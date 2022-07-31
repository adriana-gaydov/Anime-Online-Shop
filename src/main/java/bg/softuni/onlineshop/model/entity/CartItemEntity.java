package bg.softuni.onlineshop.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_items")
public class CartItemEntity extends BaseEntity {

    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
    private ProductEntity product;

    private long quantity;

    public CartItemEntity() {
    }

    public ProductEntity getProduct() {
        return product;
    }

    public CartItemEntity setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

    public long getQuantity() {
        return quantity;
    }

    public CartItemEntity setQuantity(long quantity) {
        this.quantity = quantity;
        return this;
    }
}
