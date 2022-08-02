package bg.softuni.onlineshop.service;

import bg.softuni.onlineshop.model.dto.UserEditDTO;
import bg.softuni.onlineshop.model.dto.UserLoginDTO;
import bg.softuni.onlineshop.model.dto.UserRegisterDTO;
import bg.softuni.onlineshop.model.entity.UserEntity;
import bg.softuni.onlineshop.model.view.UserViewModel;

import java.util.List;

public interface UserService {

    void initAdmin();
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmailAndPassword(String email, String password);
    Long register(UserRegisterDTO userRegisterModel);

    UserEntity findByEmail(String email);

    void save(UserEntity currentUser);

    UserViewModel findById(Long id);

    UserEntity entityFindById(Long id);

    List<UserViewModel> getAllUsers();

    void disableById(Long id);

    void enableById(Long id);

    void updateUser(UserEditDTO userEditDTO, UserEntity user);

    void deleteByEmail(String s);

    void makeAdminById(Long id);

    void removeAdminById(Long id);
}
