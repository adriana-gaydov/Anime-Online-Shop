package bg.softuni.onlineshop.web;

import bg.softuni.onlineshop.model.dto.SearchDTO;
import bg.softuni.onlineshop.model.view.ProductViewModel;
import bg.softuni.onlineshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final ProductService productService;

    public SearchController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String searchQuery(@Valid SearchDTO searchDTO,
                              BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchDTO", searchDTO);
            model.addAttribute(
                    "org.springframework.validation.BindingResult.searchDTO",
                    bindingResult);
            return "product-search";
        }

        if (!model.containsAttribute("searchDTO")) {
            model.addAttribute("searchDTO", searchDTO);
        }

        if (!searchDTO.isEmpty()) {
            model.addAttribute("products", productService.searchOffer(searchDTO));
//        } else {
//            model.addAttribute("products", new ArrayList<>());
//        }
        }

        return "product-search";
    }
}
