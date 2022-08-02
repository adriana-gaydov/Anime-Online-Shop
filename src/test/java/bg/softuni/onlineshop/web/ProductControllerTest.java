package bg.softuni.onlineshop.web;

import bg.softuni.onlineshop.repository.ProductRepository;
import bg.softuni.onlineshop.service.IpStatsService;
import bg.softuni.onlineshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private IpStatsService ipStatsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllProducts() throws Exception {

        this.mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("product"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attributeExists("title"));
    }


    @Test
    void testGetAllClothing() throws Exception {

        this.mockMvc.perform(get("/products/clothing"))
                .andExpect(status().isOk())
                .andExpect(view().name("product"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attributeExists("title"));
    }

    @Test
    void testGetAllManga() throws Exception {

        this.mockMvc.perform(get("/products/manga"))
                .andExpect(status().isOk())
                .andExpect(view().name("product"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attributeExists("title"));
    }

    @Test
    void testGetAllFigure() throws Exception {

        this.mockMvc.perform(get("/products/figure"))
                .andExpect(status().isOk())
                .andExpect(view().name("product"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attributeExists("title"));
    }

    @Test
    void testGetAllFunko() throws Exception {

        this.mockMvc.perform(get("/products/funko"))
                .andExpect(status().isOk())
                .andExpect(view().name("product"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attributeExists("title"));
    }

    @Test
    void testGetAllPoster() throws Exception {

        this.mockMvc.perform(get("/products/poster"))
                .andExpect(status().isOk())
                .andExpect(view().name("product"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attributeExists("title"));
    }

    @Test
    void testGetAllPlush() throws Exception {

        this.mockMvc.perform(get("/products/plush"))
                .andExpect(status().isOk())
                .andExpect(view().name("product"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attributeExists("title"));
    }

    @Test
    void testGetProductById() throws Exception {

        this.mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product-detail"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attributeHasNoErrors("product"));
    }

    @Test
    void testGetProductByIdThrows() throws Exception {

        this.mockMvc.perform(get("/products/23232"))
                .andExpect(status().isNotFound());
    }
}
