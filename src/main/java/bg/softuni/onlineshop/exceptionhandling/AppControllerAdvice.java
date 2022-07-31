package bg.softuni.onlineshop.exceptionhandling;

import bg.softuni.onlineshop.exceptionhandling.exception.ProductNotFoundException;
import bg.softuni.onlineshop.model.dto.NewsletterDTO;
import org.hibernate.ObjectNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler({ObjectNotFoundException.class})
    public String handleError() {
        return "object-not-found";
    }
}