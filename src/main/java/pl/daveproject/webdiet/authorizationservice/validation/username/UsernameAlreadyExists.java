package pl.daveproject.webdiet.authorizationservice.validation.username;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = UsernameValidator.class)
@Documented
public @interface UsernameAlreadyExists {
    String message() default "{username.validation.error-message.already-exists}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
