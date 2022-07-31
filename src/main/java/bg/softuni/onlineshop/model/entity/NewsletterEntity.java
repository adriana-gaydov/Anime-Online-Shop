package bg.softuni.onlineshop.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "newsletter_emails")
public class NewsletterEntity extends BaseEntity {

    private String email;

    public NewsletterEntity() {
    }

    public String getEmail() {
        return email;
    }

    public NewsletterEntity setEmail(String email) {
        this.email = email;
        return this;
    }
}
