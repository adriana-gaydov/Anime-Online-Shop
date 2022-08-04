package bg.softuni.onlineshop.service;

import bg.softuni.onlineshop.model.entity.CategoryEntity;
import bg.softuni.onlineshop.model.enums.CategoryEnum;

import java.util.List;

public interface CategoryService {

    List<CategoryEntity> initCategories();

    CategoryEntity findByCategory(CategoryEnum category);
}
