package app.cta4j.body;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public record CreatePostRecord(
    @JsonProperty("$type")
    String type,

    String text,

    String createdAt
) {
    /*
    public CreatePostRecord(String text) {
        this("app.bsky.feed.post", text, DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.from(ZoneOffset.UTC))
                                                                                   .format(Instant.now()));
    }

     */
}
