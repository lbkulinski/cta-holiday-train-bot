package app.cta4j.runner;

import app.cta4j.client.bsky.BlueskyClient;
import app.cta4j.service.StatusService;
import com.rollbar.notifier.Rollbar;
import io.github.redouane59.twitter.TwitterClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import social.bigbone.MastodonClient;
import social.bigbone.api.exception.BigBoneRequestException;

import java.util.*;

@Component
public final class BotRunner implements ApplicationRunner {
    private final StatusService service;

    private final TwitterClient twitterClient;

    private final MastodonClient mastodonClient;

    private final BlueskyClient blueskyClient;

    private final Rollbar rollbar;

    private static final Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger(BotRunner.class);
    }

    @Autowired
    public BotRunner(StatusService service, TwitterClient twitterClient, MastodonClient mastodonClient,
        BlueskyClient blueskyClient, Rollbar rollbar) {
        this.service = Objects.requireNonNull(service);

        this.twitterClient = Objects.requireNonNull(twitterClient);

        this.mastodonClient = Objects.requireNonNull(mastodonClient);

        this.blueskyClient = Objects.requireNonNull(blueskyClient);

        this.rollbar = Objects.requireNonNull(rollbar);
    }

    @Override
    public void run(ApplicationArguments args) {
        String status = this.service.getStatus();

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
            this.blueskyClient.postSkeet(status);
        } catch (Exception e) {
            this.rollbar.error(e);

            String message = e.getMessage();

            BotRunner.LOGGER.error(message, e);
        }
    }
}
