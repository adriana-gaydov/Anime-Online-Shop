package bg.softuni.onlineshop.repository;

import bg.softuni.onlineshop.model.dto.SearchDTO;
import bg.softuni.onlineshop.model.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductSpecification implements Specification<ProductEntity> {

    private final SearchDTO searchDTO;

    public ProductSpecification(SearchDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    @Override
    public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        Predicate p = cb.conjunction();

        if (searchDTO.getName() != null && !searchDTO.getName().isEmpty()) {
            p.getExpressions().add(
                    cb.and(cb.like(root.get("name"), "%" + searchDTO.getName() + "%"))
            );
        }

        if (searchDTO.getMinPrice() != null) {
            p.getExpressions().add(
                    cb.and(cb.greaterThanOrEqualTo(root.get("price"), searchDTO.getMinPrice()))
            );
        }

        if (searchDTO.getMaxPrice() != null) {
            p.getExpressions().add(
                    cb.and(cb.lessThanOrEqualTo(root.get("price"), searchDTO.getMaxPrice()))
            );
        }

        return p;
    }
}
