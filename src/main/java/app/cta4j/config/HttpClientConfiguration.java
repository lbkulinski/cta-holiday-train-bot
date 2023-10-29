package app.cta4j.config;

import app.cta4j.client.TrainClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Objects;

@Configuration
public class HttpClientConfiguration {
    private final String apiKey;

    @Autowired
    public HttpClientConfiguration(@Value("${cta.train-api-key}") String apiKey) {
        this.apiKey = Objects.requireNonNull(apiKey);
    }

    @Bean
    public TrainClient trainClient() {
        String baseUrl = """
        https://lapi.transitchicago.com/api/1.0?key=%s&outputType=json""".formatted(this.apiKey);

        WebClient webClient = WebClient.create(baseUrl);

        WebClientAdapter webClientAdapter = WebClientAdapter.forClient(webClient);

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder()
                                                                 .clientAdapter(webClientAdapter)
                                                                 .build();

        return factory.createClient(TrainClient.class);
    }
}
