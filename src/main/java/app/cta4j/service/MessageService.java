package app.cta4j.service;

import app.cta4j.client.bsky.BlueskyClient;
import app.cta4j.task.BotTask;
import com.rollbar.notifier.Rollbar;
import io.github.redouane59.twitter.TwitterClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import social.bigbone.MastodonClient;
import social.bigbone.api.exception.BigBoneRequestException;

import java.util.Objects;

@Service
public final class MessageService {
    private final TwitterClient twitterClient;

    private final MastodonClient mastodonClient;

    private final BlueskyClient blueskyClient;

    private final Rollbar rollbar;

    private static final Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger(BotTask.class);
    }

    @Autowired
    public MessageService(TwitterClient twitterClient, MastodonClient mastodonClient,
        BlueskyClient blueskyClient, Rollbar rollbar) {
        this.twitterClient = Objects.requireNonNull(twitterClient);

        this.mastodonClient = Objects.requireNonNull(mastodonClient);

        this.blueskyClient = Objects.requireNonNull(blueskyClient);

        this.rollbar = Objects.requireNonNull(rollbar);
    }

    public void postStatus(String status) {
        Objects.requireNonNull(status);

        Thread.ofVirtual()
              .start(() -> {
                  try {
                      this.twitterClient.postTweet(status);
                  } catch (Exception e) {
                      this.rollbar.error(e);

                      String message = e.getMessage();

                      MessageService.LOGGER.error(message, e);
                  }
              });

        Thread.ofVirtual()
              .start(() -> {
                  try {
                      this.mastodonClient.statuses()
                                         .postStatus(status)
                                         .execute();
                  } catch (BigBoneRequestException e) {
                      this.rollbar.error(e);

                      String message = e.getMessage();

                      MessageService.LOGGER.error(message, e);
                  }
              });

        Thread.ofVirtual()
              .start(() -> {
                  try {
                      this.blueskyClient.postSkeet(status);
                  } catch (Exception e) {
                      this.rollbar.error(e);

                      String message = e.getMessage();

                      MessageService.LOGGER.error(message, e);
                  }
              });
    }
}
