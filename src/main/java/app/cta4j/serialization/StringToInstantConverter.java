package app.cta4j.serialization;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public final class StringToInstantConverter extends StdConverter<String, Instant> {
    @Override
    public Instant convert(String string) {
        Objects.requireNonNull(string);

        ZoneId chicagoId = ZoneId.of("America/Chicago");

        return LocalDateTime.parse(string)
                            .atZone(chicagoId)
                            .toInstant();
    }
}
