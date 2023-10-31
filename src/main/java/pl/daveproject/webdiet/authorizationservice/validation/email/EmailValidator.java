package pl.daveproject.webdiet.authorizationservice.validation.email;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.daveproject.webdiet.authorizationservice.applicationuser.service.ApplicationUserService;

@Component
@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<EmailAlreadyExists, String> {

    private final ApplicationUserService applicationUserService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !applicationUserService.existsByEmail(email);
    }
}
