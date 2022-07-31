package bg.softuni.onlineshop.web;

import bg.softuni.onlineshop.model.dto.NewsletterDTO;
import bg.softuni.onlineshop.service.NewsletterService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/newsletter")
public class NewsletterController {

    private final NewsletterService newsletterService;

    public NewsletterController(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }


    @PostMapping("/add")
    public String addNewEmail(@Valid NewsletterDTO newsletterDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("newsletterDTO", newsletterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newsletterDTO", bindingResult);

            return "redirect:/";
        }


        if(!this.newsletterService.addEmail(newsletterDTO)) {
            redirectAttributes.addFlashAttribute("emailExists", true);
            redirectAttributes.addFlashAttribute("newsletterDTO", newsletterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newsletterDTO", bindingResult);
            return "redirect:/";
        }

        return "redirect:/";
    }

}
