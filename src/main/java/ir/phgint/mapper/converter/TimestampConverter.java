package ir.phgint.mapper.converter;


import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("TimestampConverter")
@Scope("singleton")
public class TimestampConverter extends BidirectionalConverter<Long, Date> {
    @Override
    public Date convertTo(Long aLong, Type<Date> type) {
        return new Date(aLong);
    }

    @Override
    public Long convertFrom(Date date, Type<Long> type) {
        return date.getTime();
    }
}
