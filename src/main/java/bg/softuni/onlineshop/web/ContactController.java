package bg.softuni.onlineshop.web;

import bg.softuni.onlineshop.model.dto.UserContactDTO;
import bg.softuni.onlineshop.service.impl.EmailServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/contact-us")
public class ContactController {

    private EmailServiceImpl emailService;

    public ContactController(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public String contactUs() {

        return "contact";
    }

    @PostMapping
    public String contactUs(@Valid UserContactDTO contactDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("contactDTO", contactDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.contactDTO", bindingResult);

            return "redirect:contact-us";
        }

        this.emailService.sendContactMail(contactDTO.getEmail(), contactDTO.getName(),
                contactDTO.getPhoneNumber(), contactDTO.getMessage());
        return "redirect:/";
    }


    @ModelAttribute("contactDTO")
    public UserContactDTO userContactDTO() {
        return new UserContactDTO();
    }
}
