package app.cta4j.client;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.rollbar.notifier.Rollbar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Component
public final class TwitterClient {
    private final Rollbar rollbar;

    private final OAuth10aService service;

    private final OAuth1AccessToken accessToken;

    private static final Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger(TwitterClient.class);
    }

    @Autowired
    public TwitterClient(Rollbar rollbar, @Value("${twitter.consumer-key}") String consumerKey,
        @Value("${twitter.consumer-secret}") String consumerSecret,
        @Value("${twitter.access-token}") String accessToken,
        @Value("${twitter.access-token-secret}") String accessTokenSecret) {
        this.rollbar = Objects.requireNonNull(rollbar);

        Objects.requireNonNull(consumerKey);

        Objects.requireNonNull(consumerSecret);

        Objects.requireNonNull(accessToken);

        Objects.requireNonNull(accessTokenSecret);

        ServiceBuilder serviceBuilder = new ServiceBuilder(consumerKey);

        this.service = serviceBuilder.apiSecret(consumerSecret)
                                     .build(TwitterApi.instance());

        this.accessToken = new OAuth1AccessToken(accessToken, accessTokenSecret);
    }

    public void createTweet(String text) {
        Objects.requireNonNull(text);

        String url = "https://api.twitter.com/2/tweets";

        OAuthRequest request = new OAuthRequest(Verb.POST, url);

        request.addHeader("Content-Type", "application/json");

        String payload = """
        {
            "text": "%s"
        }""".formatted(text);

        request.setPayload(payload);

        this.service.signRequest(this.accessToken, request);

        Response response;

        try {
            response = this.service.execute(request);
        } catch (IOException | ExecutionException | InterruptedException e) {
            String message = e.getMessage();

            TwitterClient.LOGGER.error(message, e);

            this.rollbar.error(e);

            return;
        }

        int code = response.getCode();

        int created = HttpStatus.CREATED.value();

        if (code == created) {
            return;
        }

        String message = "Failed to create tweet";

        TwitterClient.LOGGER.error(message);

        String responseMessage = response.getMessage();

        String body = "";

        try {
            body = response.getBody();
        } catch (IOException e) {
            String exceptionMessage = e.getMessage();

            TwitterClient.LOGGER.error(exceptionMessage, e);

            this.rollbar.error(e);
        }

        Map<String, Object> custom = Map.of(
            "message", responseMessage,
            "body", body
        );

        this.rollbar.error(message, custom);
    }
}
