package bg.softuni.onlineshop.service;

import bg.softuni.onlineshop.model.dto.NewsletterDTO;
import bg.softuni.onlineshop.model.entity.NewsletterEntity;

import java.util.List;

public interface NewsletterService {
    NewsletterEntity addEmail(NewsletterDTO newsletterDTO);

    List<String> getAllEmails();
}
