package bg.softuni.onlineshop.service.impl;

import bg.softuni.onlineshop.model.entity.CategoryEntity;
import bg.softuni.onlineshop.model.enums.CategoryEnum;
import bg.softuni.onlineshop.repository.CategoryRepository;
import bg.softuni.onlineshop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {

        if (this.categoryRepository.count() != 0) {
            return;
        }

        List<CategoryEntity> categories = Arrays.stream(CategoryEnum.values())
                .map(e -> new CategoryEntity()
                        .setCategory(e)
                        .setDescription(e + " description..."))
                .toList();

        this.categoryRepository.saveAllAndFlush(categories);
    }

    @Override
    public CategoryEntity findByCategory(CategoryEnum category) {

        return this.categoryRepository.findByCategory(category);
    }
}
