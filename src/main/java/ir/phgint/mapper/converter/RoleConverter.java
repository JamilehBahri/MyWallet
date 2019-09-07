package ir.phgint.mapper.converter;

import ir.phgint.domain.Role;
import ir.phgint.domain.repository.RoleDao;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component("RoleConverter")
@Scope("singleton")
public class RoleConverter extends BidirectionalConverter<String, Role> {

    private final static Logger logger = LoggerFactory.getLogger(RoleConverter.class);

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role convertTo(String s, Type<Role> type) {

        try {
            return roleDao.findByName(s);
        } catch (Exception ex) {
            logger.error("convertTo() => roleDao.findByName() failed, exception: " + ex.getMessage(), ex);
        }

        return null;
    }

    @Override
    public String convertFrom(Role role, Type<String> type) {
        return role.getName();
    }
}
