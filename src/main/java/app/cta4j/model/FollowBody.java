package app.cta4j.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Set;

public record FollowBody(@JsonAlias("eta") Set<Train> trains) {
}
