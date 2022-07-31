package bg.softuni.onlineshop.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewsletterDTO {

    @NotBlank(message = "Enter an email!")
    @Email(message = "Enter a valid email!")
    private String email;

    public NewsletterDTO() {
    }

    public String getEmail() {
        return email;
    }

    public NewsletterDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
