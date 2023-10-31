package pl.daveproject.webdiet.authorizationservice.validation.username;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.daveproject.webdiet.authorizationservice.applicationuser.service.ApplicationUserService;

@Component
@RequiredArgsConstructor
public class UsernameValidator implements ConstraintValidator<UsernameAlreadyExists, String> {

    private final ApplicationUserService applicationUserService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !applicationUserService.existsByUsername(username);
    }
}
