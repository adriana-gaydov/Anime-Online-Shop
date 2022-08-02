package bg.softuni.onlineshop.web;

import bg.softuni.onlineshop.config.security.CustomUserDetails;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name = "user")
    private CustomUserDetails customUserDetails;

    @Test
    @WithUserDetails(value = "admin@admin.bg")
    void testDisableById() throws Exception {

        this.mockMvc.perform(get("/admin/users/disable/2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/all-users"));

        this.mockMvc.perform(get("/admin/users/enable/2"));
    }
}
