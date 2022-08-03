package bg.softuni.onlineshop.service.impl;

import bg.softuni.onlineshop.model.dto.NewsletterDTO;
import bg.softuni.onlineshop.model.entity.NewsletterEntity;
import bg.softuni.onlineshop.model.entity.ProductEntity;
import bg.softuni.onlineshop.repository.NewsletterRepository;
import bg.softuni.onlineshop.service.NewsletterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NewsletterServiceTest {

    @Mock
    private NewsletterRepository newsletterRepository;

    private NewsletterService newsletterService;

    @BeforeEach
    void setUp() {
        this.newsletterService = new NewsletterServiceImpl(newsletterRepository);
    }

    @Test
    void testAddEmail() {

        NewsletterDTO newsletterDTO = new NewsletterDTO("test@test.com");
        NewsletterEntity expect = new NewsletterEntity().setEmail("test@test.com");

        when(newsletterRepository.findByEmail("test@test.com")).thenReturn(Optional.empty());

        when(newsletterRepository.save(Mockito.any(NewsletterEntity.class))).thenReturn(expect);
        NewsletterEntity newsletterEntity = this.newsletterService.addEmail(newsletterDTO);


        Assertions.assertEquals(expect.getEmail(), newsletterEntity.getEmail());
    }

    @Test
    void testAddEmailThrows() {

        NewsletterDTO newsletterDTO = new NewsletterDTO("test@test.com");
        NewsletterEntity expect = new NewsletterEntity().setEmail("test@test.com");

        when(newsletterRepository.findByEmail("test@test.com")).thenReturn(Optional.of(expect));
        NewsletterEntity newsletterEntity = this.newsletterService.addEmail(newsletterDTO);


        Assertions.assertNull(newsletterEntity);
    }
}
