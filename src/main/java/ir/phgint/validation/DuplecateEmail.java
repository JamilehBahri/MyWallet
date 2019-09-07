package ir.phgint.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, TYPE,FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = DuplecateEmailValidation.class)
@Documented
public @interface DuplecateEmail {


        String message() default "{ir.phgint.validation.DuplecateEmail.message}";

        Class<?>[] groups() default {};

        public abstract Class<? extends Payload>[] payload() default {};
    }


