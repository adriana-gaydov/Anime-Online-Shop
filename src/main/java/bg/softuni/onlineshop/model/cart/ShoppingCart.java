package bg.softuni.onlineshop.model.cart;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {

    private Set<CartItem> items;

    public ShoppingCart() {
        this.items = new HashSet<>();
    }

    public Set<CartItem> getItems() {
        return items;
    }

    public ShoppingCart setItems(Set<CartItem> items) {
        this.items = items;
        return this;
    }

    public BigDecimal totalPrice() {

        Set<CartItem> cartItems = this.items;
        BigDecimal total = new BigDecimal("0");

        for (CartItem cartItem : cartItems) {

            Long qty = cartItem.getQty();
            BigDecimal price = cartItem.getPrice();

            total = total.add(price.multiply(new BigDecimal(qty)));
        }
        
        return total;
    }

    public void addProduct(CartItem product) {
        if (this.items.contains(product)) {

            for (CartItem item : this.items) {
                if (item.equals(product)) {
                    item.setQty(item.getQty() + 1);
                    return;
                }
            }
        }

        this.items.add(product);
    }

    public void removeProduct(CartItem product) {
        this.items.remove(product);
    }

    public void clear() {
        this.items.clear();
    }
}
