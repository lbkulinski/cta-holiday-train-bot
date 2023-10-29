package app.cta4j.serialization;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.Objects;

public final class StringToBooleanConverter extends StdConverter<String, Boolean> {
    @Override
    public Boolean convert(String string) {
        Objects.requireNonNull(string);

        return Objects.equals(string, "1");
    }
}
