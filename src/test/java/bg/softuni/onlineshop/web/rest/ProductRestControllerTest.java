package bg.softuni.onlineshop.web.rest;

import bg.softuni.onlineshop.model.view.ProductViewModel;
import bg.softuni.onlineshop.repository.ProductRepository;
import bg.softuni.onlineshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void getProducts_twoProductsExist_ProductsReturnedAsJsonAndStatusIsOk() throws Exception {
        when(productService.getAllProducts())
                .thenReturn(List.of(
                        new ProductViewModel(1L, "testProd", new BigDecimal("12"),
                                "desc", 12L, "image.asdasd"),
                        new ProductViewModel(2L, "testProd2", new BigDecimal("5"),
                                "desc2", 45L, "image2.asdasd")));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$.[0].name", is("testProd")))
                .andExpect(jsonPath("$.[0].price", is(12)))
                .andExpect(jsonPath("$.[0].description", is("desc")))
                .andExpect(jsonPath("$.[0].quantity", is(12)))
                .andExpect(jsonPath("$.[0].imageUrl", is("image.asdasd")))
                .andExpect(jsonPath("$.[1].id", is(2)))
                .andExpect(jsonPath("$.[1].name", is("testProd2")))
                .andExpect(jsonPath("$.[1].price", is(5)))
                .andExpect(jsonPath("$.[1].description", is("desc2")))
                .andExpect(jsonPath("$.[1].quantity", is(45)))
                .andExpect(jsonPath("$.[1].imageUrl", is("image2.asdasd")));
    }

    @Test
    void getProductById_ProductReturnedAsJsonAndStatusIsOk() throws Exception {
        when(productService.getById(1L)).thenReturn(
                new ProductViewModel(1L, "testProd", new BigDecimal("12"),
                        "desc", 12L, "image.asdasd")
        );

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("testProd")))
                .andExpect(jsonPath("$.price", is(12)))
                .andExpect(jsonPath("$.description", is("desc")))
                .andExpect(jsonPath("$.quantity", is(12)))
                .andExpect(jsonPath("$.imageUrl", is("image.asdasd")));

    }

    @Test
    void getProductById_ProductThrowNotFound() throws Exception {
        mockMvc.perform(get("/api/products/12323"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.objectId", is(12323)))
                .andExpect(jsonPath("$.message", is("Product was not found!")));

    }
}
