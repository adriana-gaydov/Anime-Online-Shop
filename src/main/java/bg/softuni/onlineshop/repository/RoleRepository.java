package bg.softuni.onlineshop.repository;

import bg.softuni.onlineshop.model.entity.RoleEntity;
import bg.softuni.onlineshop.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByRole(RoleEnum role);
}
