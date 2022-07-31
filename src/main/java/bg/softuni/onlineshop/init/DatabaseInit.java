package bg.softuni.onlineshop.init;

import bg.softuni.onlineshop.service.CategoryService;
import bg.softuni.onlineshop.service.ProductService;
import bg.softuni.onlineshop.service.RoleService;
import bg.softuni.onlineshop.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {

    private final RoleService roleService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;

    public DatabaseInit(RoleService roleService, CategoryService categoryService, UserService userService, ProductService productService) {
        this.roleService = roleService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.roleService.initRoles();
        this.categoryService.initCategories();
        this.userService.initAdmin();
        this.productService.initProducts();
    }
}
