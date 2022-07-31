package bg.softuni.onlineshop.model.view;

import java.math.BigDecimal;
import java.util.List;

public class ProductViewModel {


    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Long quantity;
    private String imageUrl;

    public ProductViewModel() {
    }

    public String getName() {
        return name;
    }

    public ProductViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public ProductViewModel setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ProductViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ProductViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}
