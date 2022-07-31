package bg.softuni.onlineshop.model.dto;

import java.math.BigDecimal;

public class SearchDTO {

    private String name;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    public SearchDTO() {
    }

    public String getName() {
        return name;
    }

    public SearchDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public SearchDTO setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public SearchDTO setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public boolean isEmpty() {
        return (name == null || name.isEmpty()) &&
                minPrice == null &&
                maxPrice == null;
    }
}
