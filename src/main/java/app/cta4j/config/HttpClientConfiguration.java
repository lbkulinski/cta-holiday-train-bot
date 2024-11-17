package app.cta4j.config;

import app.cta4j.client.api.TrainApi;
import app.cta4j.client.invoker.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class HttpClientConfiguration {
    private final String apiKey;

    @Autowired
    public HttpClientConfiguration(@Value("${cta.train-api-key}") String apiKey) {
        this.apiKey = Objects.requireNonNull(apiKey);
    }

    @Bean
    public TrainApi trainApi() {
        String baseUrl = """
        https://lapi.transitchicago.com/api/1.0?key=%s&outputType=json""".formatted(this.apiKey);

        ApiClient apiClient = new ApiClient();

        apiClient.setBasePath(baseUrl);

        return new TrainApi(apiClient);
    }
}
