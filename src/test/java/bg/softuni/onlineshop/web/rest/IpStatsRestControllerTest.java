package bg.softuni.onlineshop.web.rest;

import bg.softuni.onlineshop.model.view.IpStatsViewModel;
import bg.softuni.onlineshop.service.IpStatsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;


@SpringBootTest
@AutoConfigureMockMvc
public class IpStatsRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IpStatsService ipStatsService;

    @Test
    void testGetStatsIsOk() throws Exception {

        when(ipStatsService.getStatistic())
                .thenReturn(List.of(new IpStatsViewModel(LocalDateTime.of(1, 1, 1, 1, 1), "123.0", 1L),
                        new IpStatsViewModel(LocalDateTime.of(2, 2, 2, 2, 2), "124.0", 2L)));

        mockMvc.perform(get("/api/statistic"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].localDateTime", is("0001-01-01T01:01:00")))
                .andExpect(jsonPath("$.[0].ipAddress", is("123.0")))
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$.[1].localDateTime", is("0002-02-02T02:02:00")))
                .andExpect(jsonPath("$.[1].ipAddress", is("124.0")))
                .andExpect(jsonPath("$.[1].id", is(2)));
    }
}
