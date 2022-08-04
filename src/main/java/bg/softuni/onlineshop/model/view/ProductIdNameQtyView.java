package bg.softuni.onlineshop.model.view;

public class ProductIdNameQtyView {

    private Long id;
    private String prodName;

    private Long quantity;

    public ProductIdNameQtyView() {
    }

    public Long getId() {
        return id;
    }

    public ProductIdNameQtyView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProdName() {
        return prodName;
    }

    public ProductIdNameQtyView setProdName(String prodName) {
        this.prodName = prodName;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public ProductIdNameQtyView setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }
}
