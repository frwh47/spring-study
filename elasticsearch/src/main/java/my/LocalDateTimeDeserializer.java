package my;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;


class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser,
                                     DeserializationContext deserializationContext)
            throws IOException {
        long milli = jsonParser.getLongValue();
        long second = milli / 1000;
        int nano = (int) (milli % 1000) * 1000 * 1000;
        return LocalDateTime.ofEpochSecond(second, nano, LocalDateTimeSerializer.ZONE_OFFSET);
    }
}