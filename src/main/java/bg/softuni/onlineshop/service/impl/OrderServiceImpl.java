package bg.softuni.onlineshop.service.impl;

import bg.softuni.onlineshop.config.mapping.AddressMapper;
import bg.softuni.onlineshop.config.security.CustomUserDetails;
import bg.softuni.onlineshop.model.cart.ShoppingCart;
import bg.softuni.onlineshop.model.dto.AddressDTO;
import bg.softuni.onlineshop.model.entity.*;
import bg.softuni.onlineshop.model.view.OrderAdminView;
import bg.softuni.onlineshop.model.view.ProductIdNameView;
import bg.softuni.onlineshop.repository.OrderRepository;
import bg.softuni.onlineshop.service.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ShoppingCart shoppingCart;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final AddressService addressService;
    private final ProductService productService;

    public OrderServiceImpl(OrderRepository orderRepository, ShoppingCart shoppingCart, UserService userService, ShoppingCartService shoppingCartService, AddressService addressService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.shoppingCart = shoppingCart;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.addressService = addressService;
        this.productService = productService;
    }

    @Transactional
    @Override
    public Long createOrder(AddressDTO addressModel, @AuthenticationPrincipal CustomUserDetails user) {

        AddressEntity address = AddressMapper.INSTANCE.addressDTOtoAddressEntity(addressModel);
        OrderEntity order = createOrder(user, address);

        return order.getId();
    }

    private OrderEntity createOrder(CustomUserDetails user, AddressEntity address) {
        this.addressService.save(address);

        //todo
        UserEntity currentUser = this.userService.entityFindById(user.getId());

        OrderEntity order = new OrderEntity()
                .setCustomer(currentUser);

        currentUser.addAddress(address);
        this.userService.save(currentUser);

        order.setDateCreated(LocalDate.now());
//
        List<CartItemEntity> cartItems = this.shoppingCartService.getShoppingCart().getItems()
                .stream().map(e -> {
                            ProductEntity product = this.productService.findById(e.getId()).get();
                            Long qty = e.getQty();

                            product.setQuantity(product.getQuantity() - qty);
                            productService.save(product);

                            return new CartItemEntity()
                                    .setQuantity(qty)
                                    .setProduct(product);
                        }
                ).toList();

        this.shoppingCartService.saveAll(cartItems);
        order.setItems(cartItems);
        order.setAddress(address);

        this.shoppingCartService.clearBag();
        this.orderRepository.save(order);
        return order;
    }

    @Override
    @Transactional
    public Long createOrder(Long addressId, @AuthenticationPrincipal CustomUserDetails user) {

        AddressEntity address = this.addressService.findById(addressId);

        if (address == null) {
            throw new IllegalArgumentException("no such address");
        }

        OrderEntity order = createOrder(user, address);

        return order.getId();
    }

    @Override
    public List<OrderAdminView> getAllOrders() {

        List<OrderEntity> all = this.orderRepository.findAll();

        return all.stream()
                .map(e -> new OrderAdminView()
                        .setOrderId(e.getId())
                        .setOrderDate(e.getDateCreated())
                        .setCustomerEmail(e.getCustomer().getEmail())
                        .setFullAddress(e.getAddress().toString())
                        .setTotalPrice(getTotalPrice(e))
                        .setProducts(e.getItems().stream().map(this::getProductIdNameView)
                                .toList())).toList();

    }

    @Override
    @Transactional
    public void dropTable() {
        this.orderRepository.deleteAll();
    }

    private BigDecimal getTotalPrice(OrderEntity e) {
        return totalPrice(e.getItems().stream().map(CartItemEntity::getProduct).map(ProductEntity::getPrice).toList());
    }

    private ProductIdNameView getProductIdNameView(CartItemEntity e2) {
        return new ProductIdNameView()
                .setId(e2.getProduct().getId())
                .setProdName(e2.getProduct().getName());
    }

    public BigDecimal totalPrice(List<BigDecimal> allPrices) {

        BigDecimal total = new BigDecimal("0");

        for (BigDecimal p : allPrices) {
            total = total.add(p);
        }
        return total;
    }
}
