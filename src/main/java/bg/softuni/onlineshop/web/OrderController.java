package bg.softuni.onlineshop.web;

import bg.softuni.onlineshop.exceptionhandling.exception.OrderNotFoundException;
import bg.softuni.onlineshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order-successful/{id}")
    public String orderSuccess(@PathVariable("id") Long id, Model model) throws OrderNotFoundException {

        if (!this.orderService.isExist(id)) {
            throw new OrderNotFoundException(id);
        }

        model.addAttribute("orderId", id);
        return "order-successful";
    }
}
