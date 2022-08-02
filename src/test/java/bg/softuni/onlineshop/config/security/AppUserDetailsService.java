package bg.softuni.onlineshop.config.security;

import bg.softuni.onlineshop.model.entity.RoleEntity;
import bg.softuni.onlineshop.model.entity.UserEntity;
import bg.softuni.onlineshop.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.Optional;

public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> user = this.userRepository
                .findByEmail(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }

        UserEntity userEntity = user.get();

        if (!userEntity.isActive()) {
            throw new UsernameNotFoundException("Deactivated account!");
        }

        return new CustomUserDetails(userEntity.getId(), userEntity.getEmail(), userEntity.getEncryptedPassword(),
                (userEntity.
                        getRoles().
                        stream().
                        map(this::map).
                        toList()),
                userEntity.getFullName());
    }

    private UserDetails map(UserEntity userEntity) {
        return
                User.builder().
                        username(userEntity.getEmail()).
                        password(userEntity.getEncryptedPassword()).
                        authorities(userEntity.
                                getRoles().
                                stream().
                                map(this::map).
                                toList()).
                        build();
    }

    private GrantedAuthority map(RoleEntity userRole) {
        return new SimpleGrantedAuthority("ROLE_" +
                userRole.
                        getRole().name());
    }
}
