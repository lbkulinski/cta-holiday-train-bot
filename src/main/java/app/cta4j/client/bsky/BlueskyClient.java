package app.cta4j.client.bsky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import work.socialhub.kbsky.Bluesky;
import work.socialhub.kbsky.api.entity.app.bsky.feed.FeedPostRequest;
import work.socialhub.kbsky.api.entity.com.atproto.server.ServerCreateSessionRequest;

import java.util.Objects;

@Component
public final class BlueskyClient {
    private final String handle;

    private final String appPassword;

    private final Bluesky bluesky;

    @Autowired
    public BlueskyClient(@Value("${bluesky.handle}") String handle,
        @Value("${bluesky.app-password}") String appPassword, Bluesky bluesky) {
        this.handle = Objects.requireNonNull(handle);

        this.appPassword = Objects.requireNonNull(appPassword);

        this.bluesky = Objects.requireNonNull(bluesky);
    }

    public void postSkeet(String status) {
        Objects.requireNonNull(status);

        ServerCreateSessionRequest serverCreateSessionRequest = new ServerCreateSessionRequest();

        serverCreateSessionRequest.setIdentifier(this.handle);

        serverCreateSessionRequest.setPassword(this.appPassword);

        String accessJwt = this.bluesky.server()
                                       .createSession(serverCreateSessionRequest)
                                       .getData().accessJwt;

        FeedPostRequest feedPostRequest = new FeedPostRequest(accessJwt);

        feedPostRequest.setText(status);

        this.bluesky.feed()
                    .post(feedPostRequest);
    }
}
