package bg.softuni.onlineshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {

    @GetMapping("/order-successful/{id}")
    public String orderSuccess(@PathVariable("id") Long id, Model model) {

        model.addAttribute("orderId", id);
        return "order-successful";
    }
}
