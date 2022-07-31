package bg.softuni.onlineshop.repository;

import bg.softuni.onlineshop.model.entity.NewsletterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsletterRepository extends JpaRepository<NewsletterEntity, Long> {
    Optional<NewsletterEntity> findByEmail(String email);
}
