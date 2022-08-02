package bg.softuni.onlineshop.exceptionhandling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends Throwable {
    private final Long id;
    private static final String DEFAULT_MESSAGE = "Order not found!";

    public OrderNotFoundException(Long id) {
        super(DEFAULT_MESSAGE);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
