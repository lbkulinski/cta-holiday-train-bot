package app.cta4j.config;

import app.cta4j.client.cta4j.api.TrainApi;
import app.cta4j.client.cta4j.invoker.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class HttpClientConfiguration {
    private final String backEndUrl;

    @Autowired
    public HttpClientConfiguration(@Value("${cta4j.back-end-url}") String backEndUrl) {
        this.backEndUrl = Objects.requireNonNull(backEndUrl);
    }

    @Bean
    public TrainApi trainApi() {
        ApiClient apiClient = new ApiClient();

        apiClient.setBasePath(this.backEndUrl);

        return new TrainApi(apiClient);
    }
}
