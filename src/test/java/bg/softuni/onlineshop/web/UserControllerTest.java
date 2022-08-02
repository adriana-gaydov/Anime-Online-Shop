package bg.softuni.onlineshop.web;
import bg.softuni.onlineshop.config.security.CustomUserDetails;
import bg.softuni.onlineshop.repository.UserRepository;
import bg.softuni.onlineshop.service.IpStatsService;
import bg.softuni.onlineshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Test
    void testGetAllProducts() throws Exception {

        this.mockMvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name("about"));
    }

    @Test
    @WithUserDetails("admin@admin.bg")
    void testGetUserDetails() throws Exception {

        this.mockMvc.perform(get("/users/details"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-details"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @WithUserDetails("admin@admin.bg")
    void testGetUserDetailsOrders() throws Exception {

        this.mockMvc.perform(get("/users/details/orders"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-orders"))
                .andExpect(model().attributeExists("curUserOrders"));
    }

    @Test
    @WithUserDetails("admin@admin.bg")
    void testGetUsersEdit() throws Exception {

        this.mockMvc.perform(get("/users/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-user"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @WithUserDetails("admin@admin.bg")
    void testPostUsersEdit() throws Exception {

        this.mockMvc.perform(post("/users/edit")
                        .param("firstName", "admin")
                        .param("middleName", "")
                        .param("lastName", "adminov")
                        .param("email", "admin2@admin.bg")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/details"));

        this.mockMvc.perform(post("/users/edit")
                .param("firstName", "admin")
                .param("middleName", "")
                .param("lastName", "adminov")
                .param("email", "admin@admin.bg")
                .with(csrf()));
    }
}


