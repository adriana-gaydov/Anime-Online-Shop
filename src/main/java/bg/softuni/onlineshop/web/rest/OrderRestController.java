package bg.softuni.onlineshop.web.rest;

import bg.softuni.onlineshop.model.view.OrderAdminView;
import bg.softuni.onlineshop.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public ResponseEntity<List<OrderAdminView>> getAllOrders() {

        return ResponseEntity.ok(this.orderService.getAllOrders());
    }
}
