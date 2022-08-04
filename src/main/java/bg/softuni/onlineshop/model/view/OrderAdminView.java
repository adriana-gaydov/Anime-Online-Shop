package bg.softuni.onlineshop.model.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderAdminView {

    private Long orderId;
    private List<ProductIdNameQtyView> products;
    private String customerEmail;
    private BigDecimal totalPrice;
    private LocalDate orderDate;
    private String fullAddress;

    public OrderAdminView() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public OrderAdminView setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public OrderAdminView setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderAdminView setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public OrderAdminView setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public OrderAdminView setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
        return this;
    }

    public List<ProductIdNameQtyView> getProducts() {
        return products;
    }

    public OrderAdminView setProducts(List<ProductIdNameQtyView> products) {
        this.products = products;
        return this;
    }
}
