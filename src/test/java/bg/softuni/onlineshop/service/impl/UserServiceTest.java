package bg.softuni.onlineshop.service.impl;

import bg.softuni.onlineshop.model.entity.UserEntity;
import bg.softuni.onlineshop.repository.UserRepository;
import bg.softuni.onlineshop.service.RoleService;
import bg.softuni.onlineshop.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmailServiceImpl emailService;
    private UserService userService;

    @BeforeEach
    void setUp() {
        this.passwordEncoder = new Pbkdf2PasswordEncoder();
        this.userService = new UserServiceImpl(userRepository, roleService, passwordEncoder, emailService);
    }
//
//    @Test
//    void testExistsByEmailAndPassword() {
//
//        String email = "asd";
//        String pass = "123";
//
//        String encode = passwordEncoder.encode(pass);
//
//        when(userRepository.findByEmailAndEncryptedPassword(email, pass)).thenReturn(true);
//
//        boolean returned = this.userService.existsByEmailAndPassword(email, pass);
//
//        Assertions.assertTrue(returned);
//    }

    @Test
    void testFindByEmail() {

        String email = "asd";
        UserEntity user = new UserEntity().setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        UserEntity returned = this.userService.findByEmail(email);

        Assertions.assertEquals(returned.getEmail(), user.getEmail());
    }
}
