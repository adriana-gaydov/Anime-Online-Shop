package bg.softuni.onlineshop.model.dto;

import bg.softuni.onlineshop.utils.messages.InvalidMessages;
import bg.softuni.onlineshop.utils.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static bg.softuni.onlineshop.utils.messages.InvalidMessages.*;
import static bg.softuni.onlineshop.utils.messages.InvalidMessages.INVALID_EMAIL;

public class UserLoginDTO {

    @NotBlank(message = EMPTY_EMAIL)
    @Email(message = INVALID_EMAIL)
    private String email;

    @NotBlank(message = "You must enter a password!")
    private String password;

    public UserLoginDTO() {
    }

    public String getEmail() {
        return email;
    }

    public UserLoginDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
