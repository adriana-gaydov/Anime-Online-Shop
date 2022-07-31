package bg.softuni.onlineshop.model.dto;

import bg.softuni.onlineshop.model.enums.CategoryEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProductAddDTO {

    @NotNull(message = "Name must not be empty!")
    @Size(min = 3, message = "Name must be at least 3 characters!")
    private String name;

    @NotNull(message = "Price must not be empty!")
    @Positive(message = "Enter positive price!")
    private BigDecimal price;

    @NotNull(message = "Description must not be empty!")
    @Size(min = 5, message = "Description must be at least 5 symbols!")
    private String description;

    @NotNull(message = "Category must be selected!")
    private CategoryEnum category;

    @NotNull(message = "Quantity must not be null!")
    @Positive(message = "Enter positive quantity!")
    private Long quantity;

    @NotNull(message = "Select a picture!")
    private MultipartFile file;

    public ProductAddDTO() {
    }

    public String getName() {
        return name;
    }

    public ProductAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductAddDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductAddDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public ProductAddDTO setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public ProductAddDTO setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public MultipartFile getFile() {
        return file;
    }

    public ProductAddDTO setFile(MultipartFile file) {
        this.file = file;
        return this;
    }
}
