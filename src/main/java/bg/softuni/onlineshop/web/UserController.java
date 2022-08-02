package bg.softuni.onlineshop.web;

import bg.softuni.onlineshop.config.security.CustomUserDetails;
import bg.softuni.onlineshop.exceptionhandling.exception.ProductNotFoundException;
import bg.softuni.onlineshop.exceptionhandling.exception.UserNotFoundException;
import bg.softuni.onlineshop.model.dto.ProductEditDTO;
import bg.softuni.onlineshop.model.dto.UserEditDTO;
import bg.softuni.onlineshop.model.entity.ProductEntity;
import bg.softuni.onlineshop.model.entity.UserEntity;
import bg.softuni.onlineshop.model.view.UserViewModel;
import bg.softuni.onlineshop.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/users/details")
    public String getUserDetails(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                 Model model) {

        Long id = getCurrentId(customUserDetails);

        UserViewModel userViewModel = this.userService.findById(id);

        model.addAttribute("user", userViewModel);
        return "user-details";
    }

    @GetMapping("/users/details/orders")
    public String getUserOrders(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                Model model) {

        Long id = getCurrentId(customUserDetails);

        UserViewModel userViewModel = this.userService.findById(id);

        if (userViewModel == null) {
            throw new UserNotFoundException(id);
        }

        model.addAttribute("curUserOrders", userViewModel.getOrders());

        return "user-orders";
    }

    private Long getCurrentId(CustomUserDetails customUserDetails) {
        Long id = customUserDetails.getId();
        return id;
    }

    @GetMapping("/users/edit")
    public String editUser(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                           Model model) {

        Long id = customUserDetails.getId();

        UserViewModel user = this.userService.findById(id);

        model.addAttribute("user", new UserEditDTO()
                .setFirstName(user.getFirstName())
                .setMiddleName(user.getMiddleName())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail()));

        return "edit-user";
    }

    @PostMapping("/users/edit")
    public String updateProduct(@AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Valid UserEditDTO userEditDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        Long id = customUserDetails.getId();

        UserEntity user = this.userService.entityFindById(id);

        if (user == null) {
            throw new UserNotFoundException(id);
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userEditDTO", userEditDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userEditDTO", bindingResult);

            return "redirect:/users/edit";
        }

      try {
          this.userService.updateUser(userEditDTO, user);
      } catch (IllegalArgumentException e) {
          redirectAttributes.addFlashAttribute("userEditDTO", userEditDTO);
          redirectAttributes.addFlashAttribute("notUniqueEmail", true);
          redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userEditDTO", bindingResult);

          return "redirect:/users/edit";
      }

        return "redirect:/users/details";
    }

}
