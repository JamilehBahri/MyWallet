package ir.phgint.validation;

import ir.phgint.domain.dto.UserProfileDto;
import ir.phgint.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component ("duplicateemail")
 public class DuplecateEmailValidation implements ConstraintValidator <DuplecateEmail, UserProfileDto>{


    @Autowired
    private UserServices userServicesImpl;


    public UserServices getUserServicesImpl() {
        return userServicesImpl;
    }

    public void setUserServicesImpl(UserServices userServicesImpl) {
        this.userServicesImpl = userServicesImpl;
    }

    @Override
    public void initialize(DuplecateEmail duplecateEmail) {


    }

    @Override
    public boolean isValid(UserProfileDto userProfileDto, ConstraintValidatorContext constraintValidatorContext)
    {

        // for save user check id
        if(userProfileDto.getId() == null){
            UserProfileDto user = userServicesImpl.findByEmail(userProfileDto.getEmail());
            if(user== null) {
                return true;
            }
            return false;
        }
        // for update user, if user has id
        else {
            //prevent fake id
            UserProfileDto user = userServicesImpl.findById(userProfileDto.getId());
            UserProfileDto user2 = userServicesImpl.findByEmail(user.getEmail());
            if(user2== null) {
                return true;
            }
            return false;
        }

    }
}
