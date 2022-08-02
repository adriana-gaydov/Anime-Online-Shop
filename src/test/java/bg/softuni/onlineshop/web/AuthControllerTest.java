package bg.softuni.onlineshop.web;


import bg.softuni.onlineshop.service.UserService;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import javax.mail.internet.MimeMessage;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Value("${spring.mail.host}")
    private String mailHost;
    @Value("${spring.mail.port}")
    private Integer mailPort;
    @Value("${spring.mail.username}")
    private String userName;
    @Value("${spring.mail.password}")
    private String password;

    private GreenMail greenMail;
    @Autowired
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        greenMail = new GreenMail(new ServerSetup(mailPort, mailHost, "smtp"));
        greenMail.start();
        greenMail.setUser(userName, password);
    }

    @AfterEach
    void tearDown() {
        greenMail.stop();
    }

    @Test
    void testRegistration() throws Exception {
        mockMvc.perform(post("/users/register").
                                param("email", "test@test.bg").
                                param("firstName", "test").
                                param("lastName", "test").
                                param("password", "Kotekote1!").
                                param("confirmPassword", "Kotekote1!").
                                param("phoneNumber", "2342342").
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("login"));

        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
        Assertions.assertEquals(1, receivedMessages.length);

        MimeMessage welcomeMessage = receivedMessages[0];

        Assertions.assertTrue(welcomeMessage.getContent().toString().
                contains("test"));

        userService.deleteByEmail("test@test.bg");
    }

    @Test
    void testLogin() throws Exception {

        mockMvc.perform(post("/users/login").
                param("email", "admin@admin.bg").
                param("password", "123").
                with(csrf()))
                .andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/"));
    }

    @Test
    void testLongInvalid() throws Exception {
        mockMvc.perform(post("/users/login").
                        param("email", "blabla@bla.bg").
                        param("password", "123132123").
                        with(csrf()))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void testLoginError() throws Exception {
        mockMvc.perform(post("/users/login-error")
                .param("email", "admin@admin.bg")
                .param("password", "123")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }
}
