package ir.phgint.mapper.converter;

import ir.phgint.domain.Gender;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("GenderConverter")
@Scope("singleton")
public class GenderConverter extends BidirectionalConverter<String, Gender> {
    @Override
    public Gender convertTo(String s, Type<Gender> type) {

        try {
            return Gender.valueOf(s);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String convertFrom(Gender gender, Type<String> type) {
        return gender.name();
    }
}
