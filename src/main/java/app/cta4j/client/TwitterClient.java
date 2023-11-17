package app.cta4j.client;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.rollbar.notifier.Rollbar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
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
        @Value("${twitter.consumer-secret}") String consumerSecret) {
        this.rollbar = Objects.requireNonNull(rollbar);

        Objects.requireNonNull(consumerKey);

        Objects.requireNonNull(consumerSecret);

        ServiceBuilder serviceBuilder = new ServiceBuilder(consumerKey);

        this.service = serviceBuilder.apiSecret(consumerSecret)
                                     .build(TwitterApi.instance());

        this.accessToken = TwitterClient.getAccessToken(this.service);
    }

    private static OAuth1AccessToken getAccessToken(OAuth10aService service) {
        Objects.requireNonNull(service);

        OAuth1RequestToken requestToken;

        try {
            requestToken = service.getRequestToken();
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        String authorizationUrl = service.getAuthorizationUrl(requestToken);

        System.out.println(authorizationUrl);

        System.out.print(">> ");

        Scanner scanner = new Scanner(System.in);

        String verifier = scanner.nextLine();

        OAuth1AccessToken accessToken;

        try {
            accessToken = service.getAccessToken(requestToken, verifier);
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return accessToken;
    }

    public void createTweet(String text) {
        String url = "https://api.twitter.com/2/tweets";

        OAuthRequest request = new OAuthRequest(Verb.POST, url);

        request.addHeader("Content-Type", "application/json");

        String payload = """
        {
            "text": "%s"
        }""".formatted(text);

        request.setPayload(payload);

        this.service.signRequest(this.accessToken, request);

        try {
            this.service.execute(request);
        } catch (IOException | ExecutionException | InterruptedException e) {
            String message = e.getMessage();

            TwitterClient.LOGGER.error(message, e);

            this.rollbar.error(e);
        }
    }
}
