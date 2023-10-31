package pl.daveproject.webdiet.authorizationservice.validation.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.daveproject.webdiet.authorizationservice.applicationuser.model.ApplicationUserSignUpRequest;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, ApplicationUserSignUpRequest> {
    @Override
    public boolean isValid(ApplicationUserSignUpRequest request, ConstraintValidatorContext constraintValidatorContext) {
        return request.getPassword().equals(request.getConfirmPassword());
    }
}
