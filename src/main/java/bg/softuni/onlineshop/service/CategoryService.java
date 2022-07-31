package bg.softuni.onlineshop.service;

import bg.softuni.onlineshop.model.entity.CategoryEntity;
import bg.softuni.onlineshop.model.enums.CategoryEnum;

public interface CategoryService {

    void initCategories();

    CategoryEntity findByCategory(CategoryEnum category);
}
