package bg.softuni.onlineshop.model.dto;

import bg.softuni.onlineshop.utils.messages.InvalidMessages;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserContactDTO {

    @NotBlank(message = "Name must not be empty!")
    private String name;

    @NotBlank(message = InvalidMessages.EMPTY_EMAIL)
    @Email(message = InvalidMessages.INVALID_EMAIL)
    private String email;

    @NotBlank(message = "Phone number must not be empty!")
    private String phoneNumber;

    @NotBlank(message = "Message must not be empty!")
    private String message;

    public UserContactDTO() {
    }

    public String getName() {
        return name;
    }

    public UserContactDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserContactDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserContactDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public UserContactDTO setMessage(String message) {
        this.message = message;
        return this;
    }
}
