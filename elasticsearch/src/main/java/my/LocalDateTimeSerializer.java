package my;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    static final ZoneOffset ZONE_OFFSET = ZoneOffset.UTC;

    @Override
    public void serialize(LocalDateTime localDateTime,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException {
        long milli = localDateTime.toInstant(ZONE_OFFSET).toEpochMilli();
        jsonGenerator.writeNumber(milli);
    }
}

