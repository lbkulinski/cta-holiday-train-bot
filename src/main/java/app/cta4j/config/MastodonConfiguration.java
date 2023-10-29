package app.cta4j.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import social.bigbone.MastodonClient;

import java.util.Objects;

@Configuration
public class MastodonConfiguration {
    @Bean
    public MastodonClient mastodonClient(@Value("${mastodon.instance}") String instance,
        @Value("${mastodon.access-token}") String accessToken) {
        Objects.requireNonNull(instance);

        Objects.requireNonNull(accessToken);

        MastodonClient.Builder builder = new MastodonClient.Builder(instance);

        return builder.accessToken(accessToken)
                      .build();
    }
}
