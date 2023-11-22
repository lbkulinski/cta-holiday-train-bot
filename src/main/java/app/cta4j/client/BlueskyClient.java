package app.cta4j.client;

import bsky4j.BlueskyFactory;
import bsky4j.api.entity.atproto.server.ServerCreateSessionRequest;
import bsky4j.api.entity.atproto.server.ServerCreateSessionResponse;
import bsky4j.api.entity.bsky.feed.FeedPostRequest;
import bsky4j.domain.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public final class BlueskyClient {
    private final String handle;

    private final String appPassword;

    @Autowired
    public BlueskyClient(@Value("${bluesky.handle}") String handle,
        @Value("${bluesky.app-password}") String appPassword) {
        this.handle = Objects.requireNonNull(handle);

        this.appPassword = Objects.requireNonNull(appPassword);
    }

    public void createPost(String text) {
        Objects.requireNonNull(text);

        String uri = Service.BSKY_SOCIAL.getUri();

        ServerCreateSessionRequest request = ServerCreateSessionRequest.builder()
                                                                       .identifier(this.handle)
                                                                       .password(this.appPassword)
                                                                       .build();

        ServerCreateSessionResponse createSessionResponse = BlueskyFactory.getInstance(uri)
                                                                          .server()
                                                                          .createSession(request)
                                                                          .get();

        String accessJwt = createSessionResponse.getAccessJwt();

        FeedPostRequest feedPostRequest = FeedPostRequest.builder()
                                                         .accessJwt(accessJwt)
                                                         .text(text)
                                                         .build();

        BlueskyFactory.getInstance(uri)
                      .feed()
                      .post(feedPostRequest)
                      .get();
    }
}
