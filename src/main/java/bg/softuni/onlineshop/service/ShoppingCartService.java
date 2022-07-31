package bg.softuni.onlineshop.service;

import bg.softuni.onlineshop.exceptionhandling.exception.NotEnoughProductsInStockException;
import bg.softuni.onlineshop.model.cart.CartItem;
import bg.softuni.onlineshop.model.cart.ShoppingCart;
import bg.softuni.onlineshop.model.entity.CartItemEntity;
import bg.softuni.onlineshop.model.entity.ProductEntity;

import java.util.List;

public interface ShoppingCartService {

    void addProduct(CartItem product);

    void removeProduct(CartItem product);

    void checkout() throws NotEnoughProductsInStockException;

    void canCheckout() throws NotEnoughProductsInStockException;

    ShoppingCart getShoppingCart();

    List<ProductEntity> getAllProducts();

    void saveAll(List<CartItemEntity> cartItems);

    void clearBag();

    boolean isEmpty();
}
