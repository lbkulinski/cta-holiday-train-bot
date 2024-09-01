package app.cta4j.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.socialhub.kbsky.Bluesky;
import work.socialhub.kbsky.BlueskyFactory;
import work.socialhub.kbsky.domain.Service;

import java.util.Objects;

@Configuration
public class BlueskyConfiguration {
    @Bean
    public Bluesky bluesky(@Value("${bluesky.handle}") String handle,
        @Value("${bluesky.app-password}") String appPassword) {
        Objects.requireNonNull(handle);

        Objects.requireNonNull(appPassword);

        String uri = Service.BSKY_SOCIAL.getUri();

        return BlueskyFactory.INSTANCE.instance(uri);
    }
}
