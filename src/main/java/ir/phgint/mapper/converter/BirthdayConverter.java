package ir.phgint.mapper.converter;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component("BirthdayConverter")
@Scope("singleton") // by default is singleton
public class BirthdayConverter extends BidirectionalConverter<String, Date> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    public Date convertTo(String time, Type<Date> destinationType) {

        if(StringUtils.isEmpty(time))
            return null;

        try {
            return sdf.parse(time);
        } catch (ParseException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    public String convertFrom(Date time, Type<String> destinationType) {
        return sdf.format(time);
    }
}
