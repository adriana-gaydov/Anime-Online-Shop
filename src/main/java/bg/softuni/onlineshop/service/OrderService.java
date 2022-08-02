package bg.softuni.onlineshop.service;

import bg.softuni.onlineshop.config.security.CustomUserDetails;
import bg.softuni.onlineshop.model.dto.AddressDTO;
import bg.softuni.onlineshop.model.view.OrderAdminView;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface OrderService {

    Long createOrder(AddressDTO addressModel, @AuthenticationPrincipal CustomUserDetails user);

    Long createOrder(Long addressId, @AuthenticationPrincipal CustomUserDetails user);

    List<OrderAdminView> getAllOrders();

    void dropTable();

    boolean isExist(Long id);
}
