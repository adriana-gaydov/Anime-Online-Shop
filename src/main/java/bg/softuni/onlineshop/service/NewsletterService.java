package bg.softuni.onlineshop.service;

import bg.softuni.onlineshop.model.dto.NewsletterDTO;

import java.util.List;

public interface NewsletterService {
    boolean addEmail(NewsletterDTO newsletterDTO);

    List<String> getAllEmails();
}
