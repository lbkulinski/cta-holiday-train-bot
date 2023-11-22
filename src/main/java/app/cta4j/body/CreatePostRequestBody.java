package app.cta4j.body;

public record CreatePostRequestBody(
    String repo,

    String collection,

    CreatePostRecord record
) {
    /*
    public CreatePostRequestBody(String repo, CreatePostRecord record) {
        this(repo, "app.bsky.feed.post", record);
    }

     */
}
