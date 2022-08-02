package bg.softuni.onlineshop.config.security;

import bg.softuni.onlineshop.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/**/*.js", "/**/*.css", "/api/**").permitAll()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/icon/**").permitAll()
                .antMatchers("/", "/about", "/contact-us", "/newsletter/**").permitAll()
                .antMatchers( "/products", "/products/clothing", "/products/manga", "/products/funko", "/products/figure",
                        "/products/poster", "/products/plush", "/products/**", "/search/**").permitAll()
                .antMatchers("/users/login", "/users/register").anonymous()
                .antMatchers("/admin/**").hasRole("ADMINISTRATOR")
                .antMatchers("/users/logout", "/users/details/**", "/shopping-cart/**", "/users/edit").authenticated()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/users/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .failureForwardUrl("/users/login-error")
                .defaultSuccessUrl("/", true)
                .permitAll()
                .and()
                .logout(logout -> logout
                        .logoutUrl("/users/logout")
                        .logoutSuccessUrl("/")
                        .logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler())
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new AppUserDetailsService(userRepository);
    }
}
