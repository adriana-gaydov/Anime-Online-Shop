package bg.softuni.onlineshop.web.rest;

import bg.softuni.onlineshop.model.view.UserViewModel;
import bg.softuni.onlineshop.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetAllUsers_JsonAndStatusOk() throws Exception {

        when(userService.getAllUsers())
                .thenReturn(List.of(new UserViewModel(1L, "Test", "Testov", "Testov",
                        "test@test.com", true, new ArrayList<>(), new ArrayList<>()),
                        new UserViewModel(2L, "Test2", "Testov2", "Testov2",
                                "test2@test.com", true, new ArrayList<>(), new ArrayList<>())));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$.[0].firstName", is("Test")))
                .andExpect(jsonPath("$.[0].middleName", is("Testov")))
                .andExpect(jsonPath("$.[0].lastName", is("Testov")))
                .andExpect(jsonPath("$.[0].email", is("test@test.com")))
                .andExpect(jsonPath("$.[0].active", is(true)));
    }

    @Test
    void testGetUserById_JsonAndStatusOk() throws Exception {

        when(userService.findById(1L))
                .thenReturn(new UserViewModel(1L, "Test", "Testov", "Testov",
                        "test@test.com", true, new ArrayList<>(), new ArrayList<>()));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Test")))
                .andExpect(jsonPath("$.middleName", is("Testov")))
                .andExpect(jsonPath("$.lastName", is("Testov")))
                .andExpect(jsonPath("$.email", is("test@test.com")))
                .andExpect(jsonPath("$.active", is(true)));
    }

    @Test
    void testGetUserById_throws404() throws Exception {
        mockMvc.perform(get("/api/users/12323"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.objectId", is(12323)))
                .andExpect(jsonPath("$.message", is("User was not found!")));
    }
}

