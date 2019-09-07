package ir.phgint.mapper.mappers;

import ir.phgint.domain.UserProfile;
import ir.phgint.domain.dto.UserProfileDto;
import ir.phgint.mapper.AbstractObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("UserProfileMapper")
@Scope("singleton")
public class UserProfileMapper extends AbstractObjectMapper<UserProfile, UserProfileDto> {

    @Override
    protected void setupMapperFactory() {

        getMapperFactory().classMap(UserProfile.class, UserProfileDto.class)
                .mapNulls(true).mapNullsInReverse(true)
                .fieldMap( "gender", "gender").converter("GenderConverter").add()
                .fieldMap( "birthday", "birthday").converter("BirthdayConverter").add()
                .fieldMap("role", "role").converter("RoleConverter").add()
                .byDefault()
                .register();
    }

}