package bg.softuni.onlineshop.service.impl;

import bg.softuni.onlineshop.model.entity.RoleEntity;
import bg.softuni.onlineshop.model.enums.RoleEnum;
import bg.softuni.onlineshop.repository.RoleRepository;
import bg.softuni.onlineshop.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initRoles() {

        if (this.roleRepository.count() != 0) {
            return;
        }

        List<RoleEntity> roles = Arrays.stream(RoleEnum.values())
                .map(e -> new RoleEntity().setRole(e))
                .toList();

        this.roleRepository.saveAllAndFlush(roles);
    }

    @Override
    public RoleEntity findByRole(RoleEnum role) {
        return this.roleRepository.findByRole(role);
    }
}
