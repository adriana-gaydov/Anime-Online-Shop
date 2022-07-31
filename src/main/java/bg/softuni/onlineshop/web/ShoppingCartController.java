package bg.softuni.onlineshop.web;

import bg.softuni.onlineshop.config.mapping.ProductMapper;
import bg.softuni.onlineshop.config.security.CustomUserDetails;
import bg.softuni.onlineshop.exceptionhandling.exception.NotEnoughProductsInStockException;
import bg.softuni.onlineshop.exceptionhandling.exception.ProductNotFoundException;
import bg.softuni.onlineshop.model.dto.AddressDTO;
import bg.softuni.onlineshop.model.dto.ExistAddressDTO;
import bg.softuni.onlineshop.model.entity.ProductEntity;
import bg.softuni.onlineshop.model.entity.UserEntity;
import bg.softuni.onlineshop.service.OrderService;
import bg.softuni.onlineshop.service.ProductService;
import bg.softuni.onlineshop.service.ShoppingCartService;
import bg.softuni.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;


    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, OrderService orderService, ProductService productService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("")
    public ModelAndView viewShoppingCart() {
        ModelAndView modelAndView = new ModelAndView("shopping-cart");
        modelAndView.addObject("products", shoppingCartService.getShoppingCart());
        modelAndView.addObject("total", shoppingCartService.getShoppingCart().totalPrice());
        return modelAndView;
    }

    @GetMapping("/addProduct/{productId}")
    public String addProductToCart(@PathVariable("productId") Long productId) throws ProductNotFoundException {
        Optional<ProductEntity> product = this.productService.findById(productId);

        if (product.isEmpty()) {
            throw new ProductNotFoundException(productId);
        }

        shoppingCartService.addProduct(ProductMapper.INSTANCE.productToCartItem(product.get()));

        return "redirect:/shopping-cart";
    }

    @GetMapping("/removeProduct/{productId}")
    public String removeProductFromCart(@PathVariable("productId") Long productId) throws ProductNotFoundException {
        Optional<ProductEntity> product = this.productService.findById(productId);


        if (product.isEmpty()) {
            throw new ProductNotFoundException(productId);
        }

        shoppingCartService.removeProduct(ProductMapper.INSTANCE.productToCartItem(product.get()));
        return "redirect:/shopping-cart";
    }

    @GetMapping("/checkout")
    public ModelAndView checkout() {

        try {
            shoppingCartService.canCheckout();
        } catch (NotEnoughProductsInStockException e) {
            return viewShoppingCart().addObject("outOfStockMessage", e.getMessage());
        }

        return new ModelAndView("redirect:/shopping-cart/address");
    }

    @GetMapping("/address")
    public String address(Model model,
                          @AuthenticationPrincipal CustomUserDetails user) {

        if (this.shoppingCartService.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("curUser", getUser(user));
        return "address";
    }

    @PostMapping("/address/new")
    public String addressOrder(@Valid AddressDTO addressModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               @AuthenticationPrincipal CustomUserDetails user) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("addressModel", addressModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addressModel", bindingResult);

            return "redirect:address";
        }

        Long orderId = this.orderService.createOrder(addressModel, user);

        System.out.println("EMAILING");
        return "redirect:/order-successful/" + orderId;
    }

    @PostMapping("/address/exist")
    public String addressOrder(@Valid ExistAddressDTO existAddressModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               @AuthenticationPrincipal CustomUserDetails user) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("existAddressModel", existAddressModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.existAddressModel", bindingResult);

            return "redirect:address";
        }

        Long orderId = this.orderService.createOrder(existAddressModel.getAddress(), user);

        return "redirect:/order-successful/" + orderId;
    }

    @ModelAttribute("addressModel")
    public AddressDTO addressDTO() {
        return new AddressDTO();
    }

    @ModelAttribute("existAddressModel")
    public ExistAddressDTO existAddressDTO() {
        return new ExistAddressDTO();
    }

    private UserEntity getUser(@AuthenticationPrincipal CustomUserDetails user) {
        return this.userService.findByEmail(user.getUsername());
    }
}
