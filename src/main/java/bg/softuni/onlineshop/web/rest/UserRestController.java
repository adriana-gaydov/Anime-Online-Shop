package bg.softuni.onlineshop.web.rest;

import bg.softuni.onlineshop.exceptionhandling.exception.ProductNotFoundException;
import bg.softuni.onlineshop.exceptionhandling.exception.UserNotFoundException;
import bg.softuni.onlineshop.model.dto.ApiErrorDTO;
import bg.softuni.onlineshop.model.view.UserViewModel;
import bg.softuni.onlineshop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserViewModel> getUserInfo(@PathVariable("id") Long id) {

        UserViewModel userViewModel = this.userService.findById(id);

        if (userViewModel == null) {
            throw new UserNotFoundException(id);
        }

        return ResponseEntity.ok(userViewModel);
    }

    @GetMapping("")
    public ResponseEntity<List<UserViewModel>> getAllUsersInfo() {

        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({UserNotFoundException.class})
    public @ResponseBody ApiErrorDTO handleRESTErrors(ProductNotFoundException e) {
        return new ApiErrorDTO(e.getId(),
                "User was not found!");
    }
}
