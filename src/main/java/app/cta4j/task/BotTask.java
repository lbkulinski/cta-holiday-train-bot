package app.cta4j.task;

import app.cta4j.client.TrainClient;
import app.cta4j.client.TwitterClient;
import app.cta4j.model.FollowBody;
import app.cta4j.model.FollowResponse;
import app.cta4j.model.Train;
import com.rollbar.notifier.Rollbar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import social.bigbone.MastodonClient;
import social.bigbone.api.exception.BigBoneRequestException;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public final class BotTask {
    private final TrainClient trainClient;

    private final int run;

    private final TwitterClient twitterClient;

    private final MastodonClient mastodonClient;

    private final Rollbar rollbar;

    private static final Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger(BotTask.class);
    }

    @Autowired
    public BotTask(TrainClient trainClient, @Value("${cta.run}") int run, TwitterClient twitterClient,
        MastodonClient mastodonClient, Rollbar rollbar) {
        this.trainClient = Objects.requireNonNull(trainClient);

        this.run = run;

        this.twitterClient = Objects.requireNonNull(twitterClient);

        this.mastodonClient = Objects.requireNonNull(mastodonClient);

        this.rollbar = Objects.requireNonNull(rollbar);
    }

    private Train getNextTrain() {
        FollowResponse response;

        try {
            response = this.trainClient.followTrain(this.run);
        } catch (Exception e) {
            this.rollbar.error(e);

            String message = e.getMessage();

            BotTask.LOGGER.error(message, e);

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

        return sortedTrains.get(0);
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

    @Scheduled(cron = "*/5 * * * * *")
    public void postStatus() {
        String status = this.getStatus();

        if (status == null) {
            return;
        }

        try {
            this.twitterClient.createTweet(status);
        } catch (Exception e) {
            this.rollbar.error(e);

            String message = e.getMessage();

            BotTask.LOGGER.error(message, e);
        }

        try {
            this.mastodonClient.statuses()
                               .postStatus(status)
                               .execute();
        } catch (BigBoneRequestException e) {
            this.rollbar.error(e);

            String message = e.getMessage();

            BotTask.LOGGER.error(message, e);
        }
    }
}
