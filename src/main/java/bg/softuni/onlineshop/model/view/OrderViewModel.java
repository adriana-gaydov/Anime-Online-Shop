package bg.softuni.onlineshop.model.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderViewModel {

    private Long orderId;
    private List<ProductIdNameQtyView> products;
    private BigDecimal totalPrice;
    private LocalDate orderDate;
    private String fullAddress;

    public OrderViewModel() {
    }

    public List<ProductIdNameQtyView> getProducts() {
        return products;
    }

    public OrderViewModel setProducts(List<ProductIdNameQtyView> products) {
        this.products = products;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderViewModel setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public OrderViewModel setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public OrderViewModel setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
        return this;
    }

    public Long getOrderId() {
        return orderId;
    }

    public OrderViewModel setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }
}
