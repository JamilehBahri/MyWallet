package ir.phgint.validation;

import ir.phgint.domain.dto.UserProfileDto;
import ir.phgint.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component ("duplicateusername")
public class DuplecateUsernameValidation implements ConstraintValidator <DuplecateUsername, UserProfileDto>{


    @Autowired
    private UserServices userServicesImpl;

    public UserServices getUserServicesImpl() {
        return userServicesImpl;
    }

    public void setUserServicesImpl(UserServices userServicesImpl) {
        this.userServicesImpl = userServicesImpl;
    }

    @Override
    public void initialize(DuplecateUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserProfileDto userProfileDto, ConstraintValidatorContext constraintValidatorContext)
    {

        if(userProfileDto.getId() == null){
            UserProfileDto user = userServicesImpl.findByUsername(userProfileDto.getUsername());
            if(user== null) {
                return true;
            }
            return false;
        }
        else {
            UserProfileDto user = userServicesImpl.findById(userProfileDto.getId());
            if(user== null) {
                return false;
            }
            return true;
        }

    }
}
