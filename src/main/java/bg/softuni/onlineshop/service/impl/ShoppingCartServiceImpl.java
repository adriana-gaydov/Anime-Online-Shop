package bg.softuni.onlineshop.service.impl;

import bg.softuni.onlineshop.exceptionhandling.exception.NotEnoughProductsInStockException;
import bg.softuni.onlineshop.model.cart.CartItem;
import bg.softuni.onlineshop.model.cart.ShoppingCart;
import bg.softuni.onlineshop.model.entity.CartItemEntity;
import bg.softuni.onlineshop.model.entity.ProductEntity;
import bg.softuni.onlineshop.repository.CartItemRepository;
import bg.softuni.onlineshop.service.ProductService;
import bg.softuni.onlineshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Shopping Cart is implemented with a Map, and as a session bean
 *
 * @author Dusan
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCart shoppingCart;
    private ProductService productService;
    private CartItemRepository cartItemRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCart shoppingCart, ProductService productService, CartItemRepository cartItemRepository) {
        this.shoppingCart = shoppingCart;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public void addProduct(CartItem product) {
        this.shoppingCart.addProduct(product);
    }

    @Override
    public void removeProduct(CartItem product) {
        this.shoppingCart.removeProduct(product);
    }

    @Override
    public void checkout() throws NotEnoughProductsInStockException {

        Set<CartItem> items = this.shoppingCart.getItems();

        for (CartItem item : items) {
            ProductEntity product = this.productService.findById(item.getId()).get();

            if (item.getQty() > (product.getQuantity())) {
                throw new NotEnoughProductsInStockException();
            }

            product.setQuantity(product.getQuantity() - 1);
            this.productService.save(product);
        }

        this.shoppingCart.clear();
    }

    @Override
    public void canCheckout() throws NotEnoughProductsInStockException {
        Set<CartItem> items = this.shoppingCart.getItems();

        for (CartItem item : items) {
            ProductEntity product = this.productService.findById(item.getId()).get();

            if (item.getQty() > (product.getQuantity())) {
                throw new NotEnoughProductsInStockException();
            }
        }
    }

    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }

    @Override
    public List<ProductEntity> getAllProducts() {

        List<ProductEntity> products = this.shoppingCart.getItems()
                .stream().map(e -> this.productService.findById(e.getId()).get())
                .toList();

        return products;
    }

    @Override
    public void saveAll(List<CartItemEntity> cartItems) {
        this.cartItemRepository.saveAll(cartItems);
    }

    @Override
    public void clearBag() {
        this.shoppingCart.clear();
    }

    public boolean isEmpty() {
        return this.shoppingCart.getItems().isEmpty();
    }
}
