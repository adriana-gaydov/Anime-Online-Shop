package bg.softuni.onlineshop.service.impl;

import bg.softuni.onlineshop.model.dto.NewsletterDTO;
import bg.softuni.onlineshop.model.entity.NewsletterEntity;
import bg.softuni.onlineshop.repository.NewsletterRepository;
import bg.softuni.onlineshop.service.NewsletterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsletterServiceImpl implements NewsletterService {

    private NewsletterRepository newsletterRepository;

    public NewsletterServiceImpl(NewsletterRepository newsletterRepository) {
        this.newsletterRepository = newsletterRepository;
    }

    @Override
    public NewsletterEntity addEmail(NewsletterDTO newsletterDTO) {

        if (this.newsletterRepository.findByEmail(newsletterDTO.getEmail()).isPresent()) {
            return null;
        }

        return this.newsletterRepository.save(new NewsletterEntity()
                .setEmail(newsletterDTO.getEmail()));
    }

    @Override
    public List<String> getAllEmails() {

        return this.newsletterRepository.findAll()
                .stream().map(NewsletterEntity::getEmail)
                .toList();
    }
}
