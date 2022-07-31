package bg.softuni.onlineshop.repository;

import bg.softuni.onlineshop.model.entity.ProductEntity;
import bg.softuni.onlineshop.model.enums.CategoryEnum;
import bg.softuni.onlineshop.model.view.ProductViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {
    List<ProductEntity> findByCategoryCategory(CategoryEnum category);

    Optional<ProductEntity> findByName(String name);
}
