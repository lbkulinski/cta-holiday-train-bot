package app.cta4j.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record FollowResponse(@JsonAlias("ctatt") FollowBody body) {
}
