package bg.softuni.onlineshop.repository;

import bg.softuni.onlineshop.model.entity.CategoryEntity;
import bg.softuni.onlineshop.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByCategory(CategoryEnum category);
}
