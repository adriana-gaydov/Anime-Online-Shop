package bg.softuni.onlineshop.model.dto;

import bg.softuni.onlineshop.utils.messages.InvalidMessages;
import bg.softuni.onlineshop.utils.validation.UniqueEmail;
import bg.softuni.onlineshop.utils.validation.UniquePhoneNumber;
import bg.softuni.onlineshop.utils.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
public class UserRegisterDTO {

    @NotBlank(message = "First name must not be empty!")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last name must not be empty!")
    private String lastName;

    @NotBlank(message = InvalidMessages.EMPTY_EMAIL)
    @Email(message = InvalidMessages.INVALID_EMAIL)
    @UniqueEmail
    private String email;

    @ValidPassword
    private String password;

    //@NotEmpty(message = "Confirm password must not be empty!")
    private String confirmPassword;

    @NotBlank(message = "Phone number must not be empty!")
    @UniquePhoneNumber
    private String phoneNumber;

    public UserRegisterDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public UserRegisterDTO setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRegisterDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
