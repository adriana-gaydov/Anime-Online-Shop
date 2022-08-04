package bg.softuni.onlineshop.service.impl;

import bg.softuni.onlineshop.model.entity.CategoryEntity;
import bg.softuni.onlineshop.model.entity.ProductEntity;
import bg.softuni.onlineshop.model.enums.CategoryEnum;
import bg.softuni.onlineshop.repository.CategoryRepository;
import bg.softuni.onlineshop.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        this.categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void testInit() {
        List<CategoryEntity> categories = Arrays.stream(CategoryEnum.values())
                .map(e -> new CategoryEntity()
                        .setCategory(e)
                        .setDescription(e + " description..."))
                .toList();

        when(categoryRepository.saveAll(Mockito.any(List.class))).thenReturn(categories);
        when(categoryRepository.count()).thenReturn(0L);
        List<CategoryEntity> returned = this.categoryService.initCategories();

        Assertions.assertEquals(returned.get(0).getCategory(), categories.get(0).getCategory());
        Assertions.assertEquals(returned.get(0).getDescription(), categories.get(0).getDescription());
    }
}
