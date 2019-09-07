package ir.phgint.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD,TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = DuplecateUsernameValidation.class)
@Documented
public @interface DuplecateUsername {


        String message() default "{ir.phgint.validation.DuplecateUsername.message}";

        Class<?>[] groups() default {};

        public abstract Class<? extends Payload>[] payload() default {};
    }


