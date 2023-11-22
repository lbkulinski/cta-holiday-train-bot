package app.cta4j.body;

public record CreateSessionRequestBody(
    String identifier,

    String password
) {
}
