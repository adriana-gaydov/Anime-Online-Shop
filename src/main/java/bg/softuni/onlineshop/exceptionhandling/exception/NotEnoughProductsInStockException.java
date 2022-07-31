package bg.softuni.onlineshop.exceptionhandling.exception;

import bg.softuni.onlineshop.model.entity.ProductEntity;

public class NotEnoughProductsInStockException extends Exception {

    private static final String DEFAULT_MESSAGE = "Not enough products in stock";

    public NotEnoughProductsInStockException() {
        super(DEFAULT_MESSAGE);
    }

    public NotEnoughProductsInStockException(ProductEntity product) {
        super(String.format("Not enough %s products in stock. Only %d left", product.getName(), product.getQuantity()));
    }

}
