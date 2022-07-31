package bg.softuni.onlineshop.model.cart;

import java.math.BigDecimal;
import java.util.Objects;

public class CartItem {

    private Long id;
    private String name;
    private BigDecimal price;
    private Long qty;
    private String imageUrl;

    public CartItem() {
        this.qty = 1L;
    }

    public String getName() {
        return name;
    }

    public CartItem setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CartItem setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Long getQty() {
        return qty;
    }

    public CartItem setQty(Long qty) {
        this.qty = qty;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CartItem setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CartItem setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(id, cartItem.id) && Objects.equals(name, cartItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
