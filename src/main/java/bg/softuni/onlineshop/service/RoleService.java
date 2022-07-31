package bg.softuni.onlineshop.service;

import bg.softuni.onlineshop.model.entity.RoleEntity;
import bg.softuni.onlineshop.model.enums.RoleEnum;

public interface RoleService {

    void initRoles();
    RoleEntity findByRole(RoleEnum role);
}
