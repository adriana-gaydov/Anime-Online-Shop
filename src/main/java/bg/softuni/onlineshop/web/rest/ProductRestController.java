package bg.softuni.onlineshop.web.rest;

import bg.softuni.onlineshop.exceptionhandling.exception.ProductNotFoundException;
import bg.softuni.onlineshop.model.dto.ApiErrorDTO;
import bg.softuni.onlineshop.model.view.ProductViewModel;
import bg.softuni.onlineshop.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<ProductViewModel>> getAllProducts() {

        return ResponseEntity.ok(this.productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductViewModel> getProductById(@PathVariable("id") Long id) {

        ProductViewModel product = this.productService.getById(id);

        if (product == null) {
            throw new ProductNotFoundException(id);
        }

        return ResponseEntity.ok(product);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ProductNotFoundException.class})
    public @ResponseBody ApiErrorDTO handleRESTErrors(ProductNotFoundException e) {
        return new ApiErrorDTO(e.getId(),
                "Product was not found!");
    }
}
