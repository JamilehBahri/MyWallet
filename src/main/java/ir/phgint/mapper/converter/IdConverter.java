package ir.phgint.mapper.converter;


import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("IdConverter")
@Scope("singleton")
public class IdConverter extends BidirectionalConverter<String, UUID> {

    @Override
    public UUID convertTo(String s, Type<UUID> type) {
        try {
            return UUID.fromString(s);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String convertFrom(UUID uuid, Type<String> type) {
        return uuid.toString();
    }
}
