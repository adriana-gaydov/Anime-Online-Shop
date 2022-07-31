package bg.softuni.onlineshop.web;

import bg.softuni.onlineshop.exceptionhandling.exception.ProductNotFoundException;
import bg.softuni.onlineshop.model.dto.ProductAddDTO;
import bg.softuni.onlineshop.model.dto.ProductEditDTO;
import bg.softuni.onlineshop.model.entity.ProductEntity;
import bg.softuni.onlineshop.service.ProductService;
import bg.softuni.onlineshop.service.UserService;
import bg.softuni.onlineshop.service.cloudinary.CloudinaryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final CloudinaryService cloudinaryService;
    private final UserService userService;

    public AdminController(ProductService productService, CloudinaryService cloudinaryService, UserService userService) {
        this.productService = productService;
        this.cloudinaryService = cloudinaryService;
        this.userService = userService;
    }

    @GetMapping()
    private String admin() {

        return "admin";
    }

    @GetMapping("/add-product")
    public String addFile() {
        return "add-product";
    }

    @PostMapping("/add-product")
    public String addFile(@Valid ProductAddDTO productModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          @RequestParam("file") MultipartFile file) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productModel", productModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productModel", bindingResult);

            return "redirect:add-product";
        }

        boolean productExists = this.productService.isProductExists(productModel.getName());

        if (productExists) {
            redirectAttributes.addFlashAttribute("productExists", true);
            redirectAttributes.addFlashAttribute("productModel", productModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productModel", bindingResult);
            return "redirect:add-product";
        }

        try {
            String imageUrl = this.cloudinaryService.uploadFile(file);

            this.productService.addProduct(productModel, imageUrl);
        } catch (RuntimeException e) {
            bindingResult.addError(new FieldError("file", "file", "You must select a file!"));
            redirectAttributes.addFlashAttribute("productModel", productModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productModel", bindingResult);

            return "redirect:add-product";
        }

        return "redirect:/products";
    }

    @GetMapping("/edit-products")
    public String editProducts(Model model) {

        model.addAttribute("allProducts", this.productService.getAllProducts());
        return "edit-products";
    }

    //todo
    @GetMapping("/edit-product/{id}")
    public String editProducts(@PathVariable("id") Long id,
                               Model model) {

        Optional<ProductEntity> product = this.productService.findById(id);

        if (product.isEmpty()) {
            throw new ProductNotFoundException(id);
        }

        ProductEntity productEntity = product.get();

        model.addAttribute("curProduct",productEntity);
        model.addAttribute("editProduct",
                new ProductAddDTO()
                .setName(productEntity.getName())
                .setPrice(productEntity.getPrice())
                .setCategory(productEntity.getCategory().getCategory())
                .setDescription(productEntity.getDescription())
                .setQuantity(productEntity.getQuantity()));

        return "edit-product";
    }

    @PostMapping("/edit-product/{id}")
    public String updateProduct(
            @PathVariable(value = "id") Long id,
            @Valid ProductEditDTO productEditModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @RequestParam("file") MultipartFile file) {

        ProductEntity product = this.productService.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productEditModel", productEditModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productEditModel", bindingResult);

            return "redirect:/admin/edit-product/" + id;
        }

        try {
            String imageUrl = this.cloudinaryService.uploadFile(file);
            productEditModel.setImageUrl(imageUrl);

            this.productService.updateProduct(product, productEditModel);
        } catch (IOException e) {

            e.printStackTrace();
            productEditModel.setImageUrl(product.getImageUrl());
            this.productService.updateProduct(product, productEditModel);

            redirectAttributes.addFlashAttribute("productEditModel", productEditModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productEditModel", bindingResult);

            return "redirect:/admin/edit-product/"+id;
        }

        return "redirect:/products/"+id;
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/delete-product/{id}")
    public String deleteProductById(@PathVariable("id") Long id) {

        this.productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/all-users")
    public String getAllUsers() {
        return "all-users";
    }

    @GetMapping("/users/disable/{id}")
    public String disableUserById(@PathVariable("id") Long id) {

        this.userService.disableById(id);

        return "redirect:/admin/all-users";
    }

    @GetMapping("/users/enable/{id}")
    public String enableUserById(@PathVariable("id") Long id) {

        this.userService.enableById(id);

        return "redirect:/admin/all-users";
    }

    @ModelAttribute("productModel")
    public ProductAddDTO productModel() {
        return new ProductAddDTO();
    }

    @ModelAttribute("productModel")
    public ProductEditDTO productModel3() {
        return new ProductEditDTO();
    }

    @ModelAttribute("curProduct")
    public ProductEntity productModel2() {
        return new ProductEntity();
    }
}
