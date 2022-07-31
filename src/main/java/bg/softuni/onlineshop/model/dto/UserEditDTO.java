package bg.softuni.onlineshop.model.dto;

import bg.softuni.onlineshop.utils.validation.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserEditDTO {

    @NotNull(message = "Name must not be empty!")
    @Size(min = 3, message = "Name must be at least 3 characters!")
    private String firstName;

    private String middleName;

    @NotNull(message = "Name must not be empty!")
    @Size(min = 3, message = "Name must be at least 3 characters!")
    private String lastName;

    @NotNull(message = "Email must not be empty!")
    @Email(message = "Email must be valid!")
    private String email;

    public UserEditDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEditDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public UserEditDTO setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEditDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEditDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
