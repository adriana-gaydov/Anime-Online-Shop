package bg.softuni.onlineshop.web;

import bg.softuni.onlineshop.model.dto.UserLoginDTO;
import bg.softuni.onlineshop.model.dto.UserRegisterDTO;
import bg.softuni.onlineshop.service.UserService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static bg.softuni.onlineshop.utils.messages.InvalidMessages.*;

@Controller
@RequestMapping("/users")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(RedirectAttributes redirectAttributes,
                                @ModelAttribute("email") String email) {

        redirectAttributes.addFlashAttribute("badCredentials", true);
        redirectAttributes.addFlashAttribute("email", email);

        return "redirect:login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterDTO userRegisterModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        boolean passwordsMatch = userRegisterModel.getPassword()
                .equals(userRegisterModel.getConfirmPassword());


        if (!passwordsMatch) {
            bindingResult.addError(new FieldError("confirmPassword", "confirmPassword", PASSWORDS_NOT_MATCH));
        }

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("userRegisterModel", userRegisterModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterModel", bindingResult);

            return "redirect:register";
        }

        this.userService.register(userRegisterModel);

        System.out.println("register");
        return "redirect:login";
    }

    @ModelAttribute("userRegisterModel")
    public UserRegisterDTO userRegisterModel() {
        return new UserRegisterDTO();
    }

    @ModelAttribute("userLoginModel")
    public UserLoginDTO userLoginModel() {
        return new UserLoginDTO();
    }
}
