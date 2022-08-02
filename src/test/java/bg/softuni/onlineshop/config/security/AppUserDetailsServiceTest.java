package bg.softuni.onlineshop.config.security;

import bg.softuni.onlineshop.model.entity.RoleEntity;
import bg.softuni.onlineshop.model.entity.UserEntity;
import bg.softuni.onlineshop.model.enums.RoleEnum;
import bg.softuni.onlineshop.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    private AppUserDetailsService toTest;

    @BeforeEach
    void setUp() {
        toTest = new AppUserDetailsService(
                mockUserRepo
        );
    }

    @Test
    void testLoadUserByUsername_UserExists() {


        UserEntity testUserEntity =
                new UserEntity().
                        setEmail("test@test.bg").
                        setEncryptedPassword("123").
                        setFirstName("test").
                        setMiddleName("test").
                        setLastName("test").
                        setActive(true).
                        setActive(true).
                        setPhoneNumber("12434").
                        setRoles(
                                Set.of(
                                        new RoleEntity().setRole(RoleEnum.ADMINISTRATOR),
                                        new RoleEntity().setRole(RoleEnum.USER)
                                )
                        );

        when(mockUserRepo.findByEmail(testUserEntity.getEmail())).
                thenReturn(Optional.of(testUserEntity));

        CustomUserDetails userDetails = (CustomUserDetails)
                toTest.loadUserByUsername(testUserEntity.getEmail());

        Assertions.assertEquals(testUserEntity.getEmail(),
                userDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getFullName(), userDetails.getFullName());
        Assertions.assertEquals(testUserEntity.getEncryptedPassword(), userDetails.getPassword());
        Assertions.assertEquals(testUserEntity.getId(), userDetails.getId());
        Assertions.assertTrue(userDetails.isAccountNonExpired());
        Assertions.assertTrue(userDetails.isEnabled());
        Assertions.assertTrue(userDetails.isAccountNonLocked());
        Assertions.assertTrue(userDetails.isCredentialsNonExpired());

        var authorities = userDetails.getAuthorities();
        Assertions.assertEquals(2, authorities.size());

        var authoritiesIter = authorities.iterator();

        while (authoritiesIter.hasNext()) {
            GrantedAuthority authority = authoritiesIter.next();

            if (!(authority.getAuthority().equals("ROLE_" + RoleEnum.ADMINISTRATOR.name()) ||
                    (authority.getAuthority().equals("ROLE_" + RoleEnum.USER.name())))) {
                Assertions.fail("Roles don't match!");
            }

        }
    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist() {

        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("some_random@email.com")
        );
    }

}
