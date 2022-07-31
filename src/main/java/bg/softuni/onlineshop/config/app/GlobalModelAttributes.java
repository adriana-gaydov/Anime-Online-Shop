package bg.softuni.onlineshop.config.app;

import bg.softuni.onlineshop.model.dto.NewsletterDTO;
import bg.softuni.onlineshop.model.dto.SearchDTO;
import bg.softuni.onlineshop.model.view.ProductViewModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Component
@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute("newsletterDTO")
    public NewsletterDTO newsletterDTO() {
        return new NewsletterDTO();
    }

    @ModelAttribute("searchDTO")
    public SearchDTO searchDTO() {
        return new SearchDTO();
    }

    @ModelAttribute("productViewModel")
    public ProductViewModel productViewModel() {
        return new ProductViewModel();
    }
}
