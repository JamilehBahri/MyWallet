package ir.phgint.validation;

import ir.phgint.domain.dto.ValidationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserProfileValidator implements Validator {

////
    @Autowired private javax.validation.Validator beanValidator;

//    @Bean
//    public Validator validator() {
//        return new LocalValidatorFactoryBean();
//    }

//    public UserProfileValidator(Validator validator) {
//        this.beanValidator = validator;
//        springValidators = new HashSet<>();
//    }


    private Set<Validator> springValidators;

    public UserProfileValidator() {

        springValidators = new HashSet<>();
    }

    public void setSpringValidators(Set<Validator> springValidators) {
        this.springValidators = springValidators;
    }

    public javax.validation.Validator getBeanValidator() {
        return beanValidator;
    }

    public void setBeanValidator(javax.validation.Validator beanValidator) {
        this.beanValidator = beanValidator;
    }

    public boolean supports(Class<?> clazz) {
        return ValidationType.class.isAssignableFrom(clazz);
    }

     public void validate(Object target, Errors errors) {
        Set<ConstraintViolation<Object>> constraintViolations = beanValidator.validate(target);
            for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
                String propertyPath = constraintViolation.getPropertyPath().toString();
                String message = constraintViolation.getMessage();
                errors.rejectValue(propertyPath, "", message);
            }

            for(Validator validator: springValidators) {
                validator.validate(target, errors);
            }
     }
}
