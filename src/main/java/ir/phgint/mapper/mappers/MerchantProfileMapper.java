package ir.phgint.mapper.mappers;

import ir.phgint.domain.MerchantProfile;
import ir.phgint.domain.dto.MerchantProfileDto;
import ir.phgint.mapper.AbstractObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("MerchantProfileMapper")
@Scope("singleton")
public class MerchantProfileMapper extends AbstractObjectMapper<MerchantProfile, MerchantProfileDto> {

    @Override
    protected void setupMapperFactory() {
        getMapperFactory().classMap(MerchantProfile.class, MerchantProfileDto.class)
                .mapNulls(true).mapNullsInReverse(true)
                .byDefault()
                .register();
    }
}
