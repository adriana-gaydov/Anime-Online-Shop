package bg.softuni.onlineshop.service.impl;

import bg.softuni.onlineshop.model.dto.ProductAddDTO;
import bg.softuni.onlineshop.model.dto.ProductEditDTO;
import bg.softuni.onlineshop.model.entity.CategoryEntity;
import bg.softuni.onlineshop.model.entity.ProductEntity;
import bg.softuni.onlineshop.model.enums.CategoryEnum;
import bg.softuni.onlineshop.model.view.ProductViewModel;
import bg.softuni.onlineshop.repository.CategoryRepository;
import bg.softuni.onlineshop.repository.ProductRepository;
import bg.softuni.onlineshop.service.CategoryService;
import bg.softuni.onlineshop.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;

    private ProductService productService;
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        this.categoryService = new CategoryServiceImpl(categoryRepository);
        this.productService = new ProductServiceImpl(productRepository, categoryService);
    }

    @Test
    void getAllProductsTest() {

        ProductViewModel productViewModel = new ProductViewModel()
                .setName("test")
                .setDescription("random")
                        .setPrice(new BigDecimal("11"))
                                .setImageUrl("testURL")
                                        .setId(1L);

        ProductViewModel productViewModel2 = new ProductViewModel()
                .setName("test2")
                .setDescription("random2")
                .setPrice(new BigDecimal("12"))
                .setImageUrl("testURL2")
                .setId(2L);

        ProductEntity product = new ProductEntity()
                .setName("test")
                .setDescription("random")
                .setPrice(new BigDecimal("11"))
                .setImageUrl("testURL")
                .setId(1L);

        ProductEntity product2 = new ProductEntity()
                .setName("test2")
                .setDescription("random2")
                .setPrice(new BigDecimal("12"))
                .setImageUrl("testURL2")
                .setId(2L);

        when(productRepository.findAll()).thenReturn(List.of(product, product2));
        Assertions.assertEquals(this.productService.getAllProducts().get(0).getName(), productViewModel.getName());
        Assertions.assertEquals(this.productService.getAllProducts().get(1).getName(), productViewModel2.getName());
        Assertions.assertEquals(this.productService.getAllProducts().get(0).getPrice(), productViewModel.getPrice());
        Assertions.assertEquals(this.productService.getAllProducts().get(1).getPrice(), productViewModel2.getPrice());
        Assertions.assertEquals(this.productService.getAllProducts().get(0).getId(), productViewModel.getId());
        Assertions.assertEquals(this.productService.getAllProducts().get(1).getId(), productViewModel2.getId());
        Assertions.assertEquals(this.productService.getAllProducts().get(0).getDescription(), productViewModel.getDescription());
        Assertions.assertEquals(this.productService.getAllProducts().get(1).getDescription(), productViewModel2.getDescription());
        Assertions.assertEquals(this.productService.getAllProducts().get(0).getImageUrl(), productViewModel.getImageUrl());
        Assertions.assertEquals(this.productService.getAllProducts().get(1).getImageUrl(), productViewModel2.getImageUrl());

    }

    @Test
    void findByIdExistsTest() {

        ProductEntity product = new ProductEntity()
                .setName("test")
                .setDescription("random")
                .setPrice(new BigDecimal("11"))
                .setImageUrl("testURL")
                .setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Assertions.assertEquals(product, this.productService.findById(1L).get());
    }

    @Test
    void testAddProduct() {

        ProductAddDTO productAddDTO = new ProductAddDTO("test", new BigDecimal("12"), "desc",
                CategoryEnum.CLOTHING, 12L);

        ProductEntity product = new ProductEntity(1L, "test", new BigDecimal("12"), "desc",
                new CategoryEntity().setCategory(CategoryEnum.CLOTHING), LocalDateTime.of(1, 1, 1, 1, 1),
                12L,
                "string.url");

        when(productRepository.save(Mockito.any(ProductEntity.class))).thenReturn(product);
        ProductEntity returned = this.productService.addProduct(productAddDTO, "test.img");

        when(productRepository.findAll()).thenReturn(List.of(product));
        List<ProductEntity> products = this.productRepository.findAll();

        Assertions.assertEquals(returned.getName(), products.get(0).getName());
        Assertions.assertEquals(returned.getQuantity(), products.get(0).getQuantity());
        Assertions.assertEquals(returned.getDescription(), products.get(0).getDescription());
        Assertions.assertEquals(returned.getPrice(), products.get(0).getPrice());
    }


    @Test
    void testUpdateProduct() {

        ProductEditDTO productEditDTO = new ProductEditDTO("test2", new BigDecimal("11"), "desc2",
                CategoryEnum.CLOTHING, 12L, "random");

        ProductEntity old = new ProductEntity(1L, "test", new BigDecimal("12"), "desc",
                new CategoryEntity().setCategory(CategoryEnum.CLOTHING), LocalDateTime.of(1, 1, 1, 1, 1),
                12L,
                "string.url");

        when(productRepository.save(Mockito.any(ProductEntity.class))).thenReturn(old);
        ProductEntity returned = this.productService.updateProduct(old, productEditDTO);

        ProductEntity expected = new ProductEntity(1L, "test2", new BigDecimal("11"), "desc2",
                new CategoryEntity().setCategory(CategoryEnum.CLOTHING), LocalDateTime.of(1, 1, 1, 1, 1),
                12L,
                "random");

        when(productRepository.findAll()).thenReturn(List.of(expected));
        List<ProductEntity> products = this.productRepository.findAll();

        Assertions.assertEquals(returned.getName(), products.get(0).getName());
        Assertions.assertEquals(returned.getQuantity(), products.get(0).getQuantity());
        Assertions.assertEquals(returned.getDescription(), products.get(0).getDescription());
        Assertions.assertEquals(returned.getPrice(), products.get(0).getPrice());
    }
}
