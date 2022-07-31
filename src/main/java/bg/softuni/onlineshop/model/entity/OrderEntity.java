package bg.softuni.onlineshop.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @ManyToOne(optional = false)
    private UserEntity customer;

    @ManyToMany
    private List<CartItemEntity> items;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @ManyToOne(optional = false)
    private AddressEntity address;

    public OrderEntity() {
    }

    public UserEntity getCustomer() {
        return customer;
    }

    public OrderEntity setCustomer(UserEntity customer) {
        this.customer = customer;
        return this;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public OrderEntity setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public List<CartItemEntity> getItems() {
        return items;
    }

    public OrderEntity setItems(List<CartItemEntity> items) {
        this.items = items;
        return this;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public OrderEntity setAddress(AddressEntity address) {
        this.address = address;
        return this;
    }

    public BigDecimal getTotalPrice() {

        List<BigDecimal> allPrices = this.items.stream().map(CartItemEntity::getProduct).map(ProductEntity::getPrice).toList();
        BigDecimal total = BigDecimal.ZERO;

        for (BigDecimal price : allPrices) {

            total = total.add(price);
        }

        return total;
    }
}
