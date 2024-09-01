package app.cta4j.runner;

import app.cta4j.client.TrainClient;
import app.cta4j.model.FollowBody;
import app.cta4j.model.FollowResponse;
import app.cta4j.model.Train;
import com.rollbar.notifier.Rollbar;
import io.github.redouane59.twitter.TwitterClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import social.bigbone.MastodonClient;
import social.bigbone.api.exception.BigBoneRequestException;
import work.socialhub.kbsky.Bluesky;
import work.socialhub.kbsky.api.entity.app.bsky.feed.FeedPostRequest;
import work.socialhub.kbsky.api.entity.com.atproto.server.ServerCreateSessionRequest;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public final class BotRunner implements ApplicationRunner {
    private final TrainClient trainClient;

    private final Environment environment;

    private final TwitterClient twitterClient;

    private final MastodonClient mastodonClient;

    private final Bluesky blueskyClient;

    private final Rollbar rollbar;

    private static final Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger(BotRunner.class);
    }

    @Autowired
    public BotRunner(TrainClient trainClient, Environment environment, TwitterClient twitterClient,
        MastodonClient mastodonClient, Bluesky blueskyClient, Rollbar rollbar) {
        this.trainClient = Objects.requireNonNull(trainClient);

        this.environment = Objects.requireNonNull(environment);

        this.twitterClient = Objects.requireNonNull(twitterClient);

        this.mastodonClient = Objects.requireNonNull(mastodonClient);

        this.blueskyClient = Objects.requireNonNull(blueskyClient);

        this.rollbar = Objects.requireNonNull(rollbar);
    }

    private Train getNextTrain() {
        String runString = this.environment.getProperty("cta.run");

        if (runString == null) {
            String message = "Run is null";

            this.rollbar.error(message);

            BotRunner.LOGGER.error(message);

            return null;
        }

        int run;

        try {
            run = Integer.parseInt(runString);
        } catch (NumberFormatException e) {
            String message = "Run is not a number";

            this.rollbar.error(e);

            BotRunner.LOGGER.error(message, e);

            return null;
        }

        FollowResponse response;

        try {
            response = this.trainClient.followTrain(run);
        } catch (Exception e) {
            this.rollbar.error(e);

            String message = e.getMessage();

            BotRunner.LOGGER.error(message, e);

            return null;
        }

        if (response == null) {
            return null;
        }

        FollowBody body = response.body();

        if (body == null) {
            return null;
        }

        Set<Train> trains = body.trains();

        if ((trains == null) || trains.isEmpty()) {
            return null;
        }

        Comparator<Train> comparator = Comparator.comparing(Train::arrivalTime);

        List<Train> sortedTrains = new ArrayList<>(trains);

        sortedTrains.sort(comparator);

        return sortedTrains.getFirst();
    }

    private String getStatus() {
        Train train = this.getNextTrain();

        if (train == null) {
            return null;
        }

        String destination = train.destination();

        String line = train.line();

        int run = train.run();

        String station = train.station();

        ZoneId zoneId = ZoneId.of("America/Chicago");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

        String arrivalTime = train.arrivalTime()
                                  .atZone(zoneId)
                                  .toLocalDateTime()
                                  .format(formatter);

        return "%s-bound %s Run %d will be arriving at %s at %s".formatted(destination, line, run, station,
            arrivalTime);
    }

    private void postSkeet(String status) {
        Objects.requireNonNull(status);

        ServerCreateSessionRequest serverCreateSessionRequest = new ServerCreateSessionRequest();

        String handle = this.environment.getProperty("bluesky.handle");

        if (handle == null) {
            String message = "Bluesky handle is null";

            this.rollbar.error(message);

            BotRunner.LOGGER.error(message);

            return;
        }

        serverCreateSessionRequest.setIdentifier(handle);

        String appPassword = this.environment.getProperty("bluesky.app-password");

        if (appPassword == null) {
            String message = "Bluesky app password is null";

            this.rollbar.error(message);

            BotRunner.LOGGER.error(message);

            return;
        }

        serverCreateSessionRequest.setPassword(appPassword);

        String accessJwt = this.blueskyClient.server()
                                             .createSession(serverCreateSessionRequest)
                                             .getData().accessJwt;

        FeedPostRequest feedPostRequest = new FeedPostRequest(accessJwt);

        feedPostRequest.setText(status);

        this.blueskyClient.feed()
                          .post(feedPostRequest);
    }

    @Override
    public void run(ApplicationArguments args) {
        String status = this.getStatus();

        if (status == null) {
            return;
        }

        try {
            this.twitterClient.postTweet(status);
        } catch (Exception e) {
            this.rollbar.error(e);

            String message = e.getMessage();

            BotRunner.LOGGER.error(message, e);
        }

        try {
            this.mastodonClient.statuses()
                               .postStatus(status)
                               .execute();
        } catch (BigBoneRequestException e) {
            this.rollbar.error(e);

            String message = e.getMessage();

            BotRunner.LOGGER.error(message, e);
        }

        try {
            this.postSkeet(status);
        } catch (Exception e) {
            this.rollbar.error(e);

            String message = e.getMessage();

            BotRunner.LOGGER.error(message, e);
        }
    }
}
