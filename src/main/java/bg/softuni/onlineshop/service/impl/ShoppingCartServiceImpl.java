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

    /**
     * If product is in the map just increment quantity by 1.
     * If product is not in the map with, add it with quantity 1
     *
     * @param product
     */
    @Override
    public void addProduct(CartItem product) {
        this.shoppingCart.addProduct(product);
    }

    /**
     * If product is in the map with quantity > 1, just decrement quantity by 1.
     * If product is in the map with quantity 1, remove it from map
     *
     * @param product
     */
    @Override
    public void removeProduct(CartItem product) {
        this.shoppingCart.removeProduct(product);
    }

//    /**
//     * @return unmodifiable copy of the map
//     */
//    @Override
//    public ShoppingCart getCart() {
//        return Collections.unmodifiableMap(this.shoppingCart);
//    }

    /**
     * Checkout will rollback if there is not enough of some product in stock
     *
     * @throws NotEnoughProductsInStockException
     */
    @Override
    public void checkout() throws NotEnoughProductsInStockException {
//        ProductEntity product;
//        for (Map.Entry<ProductEntity, Integer> entry : products.entrySet()) {
//            // Refresh quantity for every product before checking
//            product = productRepository.findById(entry.getKey().getId()).get();
//            if (product.getQuantity() < entry.getValue())
//                throw new NotEnoughProductsInStockException(product);
//            entry.getKey().setQuantity(product.getQuantity() - entry.getValue());
//        }

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
//    @Override
//    public BigDecimal getTotal() {
//        return products.entrySet().stream()
//                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
//                .reduce(BigDecimal::add)
//                .orElse(BigDecimal.ZERO);
//    }

    public boolean isEmpty() {
        return this.shoppingCart.getItems().isEmpty();
    }
}
