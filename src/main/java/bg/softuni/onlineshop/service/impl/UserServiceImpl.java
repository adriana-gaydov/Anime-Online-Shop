package bg.softuni.onlineshop.service.impl;

import bg.softuni.onlineshop.config.mapping.UserMapper;
import bg.softuni.onlineshop.exceptionhandling.exception.UserNotFoundException;
import bg.softuni.onlineshop.model.dto.RoleDTO;
import bg.softuni.onlineshop.model.dto.UserEditDTO;
import bg.softuni.onlineshop.model.dto.UserRegisterDTO;
import bg.softuni.onlineshop.model.entity.CartItemEntity;
import bg.softuni.onlineshop.model.entity.OrderEntity;
import bg.softuni.onlineshop.model.entity.RoleEntity;
import bg.softuni.onlineshop.model.entity.UserEntity;
import bg.softuni.onlineshop.model.enums.RoleEnum;
import bg.softuni.onlineshop.model.view.OrderViewModel;
import bg.softuni.onlineshop.model.view.ProductIdNameView;
import bg.softuni.onlineshop.model.view.UserViewModel;
import bg.softuni.onlineshop.repository.UserRepository;
import bg.softuni.onlineshop.service.RoleService;
import bg.softuni.onlineshop.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final EmailServiceImpl emailService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder, EmailServiceImpl emailService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Override
    public void initAdmin() {

        if (this.userRepository.count() != 0) {
            return;
        }

        UserEntity user = new UserEntity()
                .setEmail("admin@admin.bg")
                .setEncryptedPassword(this.passwordEncoder.encode("123"))
                .setActive(true)
                .setFirstName("admin")
                .setLastName("adminov")
                .setPhoneNumber("112");

        user.addRole(this.roleService.findByRole(RoleEnum.USER));
        user.addRole(this.roleService.findByRole(RoleEnum.MODERATOR));
        user.addRole(this.roleService.findByRole(RoleEnum.ADMINISTRATOR));

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {

        return this.userRepository.findByPhoneNumber(phoneNumber).isPresent();
    }

    @Override
    public boolean existsByEmailAndPassword(String email, String password) {

        return this.userRepository.findByEmailAndEncryptedPassword
                (email, this.passwordEncoder.encode(password));
    }

    @Override
    public Long register(UserRegisterDTO userRegisterModel) {

        UserEntity user = UserMapper.INSTANCE.userRegisterDTOtoUser(userRegisterModel);
        user.setEncryptedPassword(passwordEncoder.encode(userRegisterModel.getPassword()));
        user.setActive(true);
        user.addRole(this.roleService.findByRole(RoleEnum.USER));

        this.userRepository.saveAndFlush(user);

        this.emailService.sendRegistrationEmail(user.getEmail(), user.getFullName());
        return user.getId();
    }

    @Override
    public UserEntity findByEmail(String email) {

        return this.userRepository.findByEmail(email)
                .orElse(null);
    }

    @Override
    public void save(UserEntity currentUser) {
        this.userRepository.save(currentUser);
    }

    @Override
    public UserViewModel findById(Long id) {

        return this.userRepository.findById(id).
                map(this::getUserViewModel)
                .orElse(null);
    }

    @Override
    public UserEntity entityFindById(Long id) {
        return this.userRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<UserViewModel> getAllUsers() {
        return this.userRepository.findAll().stream()
                .map(this::getUserViewModel)
                .toList();
    }

    @Override
    public void disableById(Long id) {

        Optional<UserEntity> optUser = this.userRepository.findById(id);

        if (optUser.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        UserEntity userEntity = optUser.get();
        userEntity.setActive(false);
        this.userRepository.save(userEntity);
    }

    @Override
    public void enableById(Long id) {

        Optional<UserEntity> optUser = this.userRepository.findById(id);

        if (optUser.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        UserEntity userEntity = optUser.get();
        userEntity.setActive(true);
        this.userRepository.save(userEntity);
    }

    @Override
    public void updateUser(UserEditDTO userEditDTO, UserEntity user) {

        if (!user.getEmail().equals(userEditDTO.getEmail()) &&
                this.userRepository.findByEmail(userEditDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User already exists!");
        }

        user.setEmail(userEditDTO.getEmail());
        user.setFirstName(userEditDTO.getFirstName());
        user.setMiddleName(userEditDTO.getMiddleName());
        user.setLastName(userEditDTO.getLastName());

        this.userRepository.save(user);
    }

    @Override
    public void deleteByEmail(String email) {

        Optional<UserEntity> user = this.userRepository.findByEmail(email);
        this.userRepository.deleteById(user.get().getId());
    }

    @Override
    public void makeAdminById(Long id) {
        Optional<UserEntity> optUser = this.userRepository.findById(id);

        if (optUser.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        UserEntity user = optUser.get();
        user.addRole(this.roleService.findByRole(RoleEnum.ADMINISTRATOR));
        this.userRepository.save(user);
    }

    @Override
    public void removeAdminById(Long id) {
        Optional<UserEntity> optUser = this.userRepository.findById(id);

        if (optUser.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        UserEntity user = optUser.get();
        user.removeRole(this.roleService.findByRole(RoleEnum.ADMINISTRATOR));
        this.userRepository.save(user);
    }

    private UserViewModel getUserViewModel(UserEntity e) {
        return new UserViewModel(e.getId(),
                e.getFirstName(),
                e.getMiddleName(),e.getLastName(),
                e.getEmail(), e.isActive(),
                e.getOrders().stream()
                .map(this::getOrderViewModel).toList(),
                getRoleDTOList(e));
//                .setId(e.getId())
//                .setFirstName(e.getFirstName())
//                .setMiddleName(e.getMiddleName())
//                .setLastName(e.getLastName())
//                .setEmail(e.getEmail())
//                .setActive(e.isActive())
//                .setOrders(e.getOrders().stream()
//                        .map(this::getOrderViewModel).toList())
//                .setRoles(getRoleDTOList(e));
    }

    private List<RoleDTO> getRoleDTOList(UserEntity e) {
        return e.getRoles()
                .stream()
                .map(r -> r.getRole().name()).map(e2 -> new RoleDTO().setName(e2))
                .toList();
    }

    private OrderViewModel getOrderViewModel(OrderEntity o) {
        return new OrderViewModel()
                .setOrderId(o.getId())
                .setFullAddress(o.getAddress().toString())
                .setOrderDate(o.getDateCreated())
                .setTotalPrice(o.getTotalPrice())
                .setProducts(o.getItems().stream().
                        map(this::getProductIdNameView).toList());
    }

    private ProductIdNameView getProductIdNameView(CartItemEntity i) {
        return new ProductIdNameView()
                .setId(i.getProduct().getId())
                .setProdName(i.getProduct().getName());
    }
}
