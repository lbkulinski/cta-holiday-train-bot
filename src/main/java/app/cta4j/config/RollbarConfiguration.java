package app.cta4j.config;

import com.rollbar.notifier.Rollbar;
import com.rollbar.notifier.config.Config;
import com.rollbar.spring.webmvc.RollbarSpringConfigBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class RollbarConfiguration {
    @Bean
    public Rollbar rollbar(@Value("${rollbar.access-token}") String accessToken,
        @Value("${rollbar.environment}") String environment) {
        Objects.requireNonNull(accessToken);

        Objects.requireNonNull(environment);

        Config config = RollbarSpringConfigBuilder.withAccessToken(accessToken)
                                                  .environment(environment)
                                                  .build();

        return new Rollbar(config);
    }
}
