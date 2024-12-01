package app.cta4j.config;

import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.signature.TwitterCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class TwitterConfiguration {
    @Bean
    public TwitterClient twitterClient(@Value("${twitter.access-token}") String accessToken,
        @Value("${twitter.access-token-secret}") String accessTokenSecret, @Value("${twitter.api-key}") String apiKey,
        @Value("${twitter.api-key-secret}") String apiKeySecret) {
        Objects.requireNonNull(accessToken);

        Objects.requireNonNull(accessTokenSecret);

        Objects.requireNonNull(apiKey);

        Objects.requireNonNull(apiKeySecret);

        TwitterCredentials credentials = TwitterCredentials.builder()
                                                           .accessToken(accessToken)
                                                           .accessTokenSecret(accessTokenSecret)
                                                           .apiKey(apiKey)
                                                           .apiSecretKey(apiKeySecret)
                                                           .build();

        TwitterClient client = new TwitterClient(credentials);

        client.setAutomaticRetry(false);

        return client;
    }
}
