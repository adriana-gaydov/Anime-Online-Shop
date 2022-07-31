package bg.softuni.onlineshop.model.view;

public class ProductIdNameView {

    private Long id;
    private String prodName;

    public ProductIdNameView() {
    }

    public Long getId() {
        return id;
    }

    public ProductIdNameView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProdName() {
        return prodName;
    }

    public ProductIdNameView setProdName(String prodName) {
        this.prodName = prodName;
        return this;
    }
}
