package pl.daveproject.webdiet.authorizationservice.validation.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.function.IntPredicate;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    private static final String SPECIAL_CHARACTER_REGEX = "[!@#$%&*()_+=|<>?{}\\[\\]~-]";
    private static final String LOWERCASE_ERROR_MESSAGE_CODE = "{password.validation.error-message.not-contains-lowercase}";
    private static final String UPPERCASE_ERROR_MESSAGE_CODE = "{password.validation.error-message.not-contains-uppercase}";
    private static final String NUMBER_ERROR_MESSAGE_CODE = "{password.validation.error-message.not-contains-number}";
    private static final String SPECIAL_CHARACTER_ERROR_MESSAGE_CODE = "{password.validation.error-message.not-contains-special-character}";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        var containsLowercase = hasAtLeasOneLowercase(password);
        var containsUppercase = hasAtLeasOneUppercase(password);
        var containsNumber = hasAtLeasOneNumber(password);
        var containsSpecialCharacter = hasAtLeasOneSpecialCharacter(password);

        if (containsLowercase && containsUppercase && containsNumber && containsSpecialCharacter) {
            return true;
        }

        if (!containsLowercase) {
            context.buildConstraintViolationWithTemplate(LOWERCASE_ERROR_MESSAGE_CODE).addConstraintViolation();
        }
        if (!containsUppercase) {
            context.buildConstraintViolationWithTemplate(UPPERCASE_ERROR_MESSAGE_CODE).addConstraintViolation();
        }
        if (!containsNumber) {
            context.buildConstraintViolationWithTemplate(NUMBER_ERROR_MESSAGE_CODE).addConstraintViolation();
        }
        if (!containsSpecialCharacter) {
            context.buildConstraintViolationWithTemplate(SPECIAL_CHARACTER_ERROR_MESSAGE_CODE).addConstraintViolation();
        }

        return false;
    }

    private boolean hasAtLeasOneLowercase(String password) {
        return contains(password, i -> Character.isLetter(i) && Character.isLowerCase(i));
    }

    private boolean hasAtLeasOneUppercase(String password) {
        return contains(password, i -> Character.isLetter(i) && Character.isUpperCase(i));
    }

    private boolean hasAtLeasOneNumber(String password) {
        return contains(password, i -> NumberUtils.isCreatable(Character.toString(i)));
    }

    private boolean hasAtLeasOneSpecialCharacter(String password) {
        var special = Pattern.compile(SPECIAL_CHARACTER_REGEX);
        return special.matcher(password).find();
    }

    private boolean contains(String value, IntPredicate predicate) {
        return value.chars().anyMatch(predicate);
    }
}
