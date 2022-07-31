package bg.softuni.onlineshop.utils.validation;

import bg.softuni.onlineshop.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePhoneNumberValidator implements ConstraintValidator<UniquePhoneNumber, String> {

    private final UserService userService;

    public UniquePhoneNumberValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        boolean isInSize = isInSize(value);

        if (!isInSize) {
            return true;
        }

        return !this.userService.existsByPhoneNumber(value);
    }

    private boolean isInSize(String value) {
        return value != null && value.length() > 0;
    }
}