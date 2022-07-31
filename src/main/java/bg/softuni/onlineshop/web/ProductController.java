package bg.softuni.onlineshop.web;

import bg.softuni.onlineshop.exceptionhandling.exception.ProductNotFoundException;
import bg.softuni.onlineshop.model.enums.CategoryEnum;
import bg.softuni.onlineshop.model.view.ProductViewModel;
import bg.softuni.onlineshop.service.ProductService;
import bg.softuni.onlineshop.service.StatisticService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final StatisticService statisticService;

    public ProductController(ProductService productService, StatisticService statisticService) {
        this.productService = productService;
        this.statisticService = statisticService;
    }

    @GetMapping("")
    public String product(Model model, HttpServletResponse response) {

        List<ProductViewModel> products = this.productService.getAllProducts();
        this.statisticService.saveVisitationInDataBase(response.getHeader("visitor"));

        model.addAttribute("products", products);
        model.addAttribute("title", "all products");

        return "product";
    }

    @GetMapping("/clothing")
    public String productsClothing(Model model, HttpServletResponse response) {

        List<ProductViewModel> clothing = this.productService.findByCategory(CategoryEnum.CLOTHING);
        this.statisticService.saveVisitationInDataBase(response.getHeader("visitor"));

        model.addAttribute("products", clothing);
        model.addAttribute("title", "clothing");

        return "product";
    }

    @GetMapping("/manga")
    public String productsManga(Model model, HttpServletResponse response) {

        List<ProductViewModel> manga = this.productService.findByCategory(CategoryEnum.MANGA);
        this.statisticService.saveVisitationInDataBase(response.getHeader("visitor"));

        model.addAttribute("products", manga);
        model.addAttribute("title", "manga");

        return "product";
    }

    @GetMapping("/funko")
    public String productsFunko(Model model, HttpServletResponse response) {

        List<ProductViewModel> funko = this.productService.findByCategory(CategoryEnum.FUNKO);
        this.statisticService.saveVisitationInDataBase(response.getHeader("visitor"));

        model.addAttribute("products", funko);
        model.addAttribute("title", "funko");

        return "product";
    }

    @GetMapping("/figure")
    public String productsFigure(Model model, HttpServletResponse response) {

        List<ProductViewModel> figure = this.productService.findByCategory(CategoryEnum.FIGURE);
        this.statisticService.saveVisitationInDataBase(response.getHeader("visitor"));

        model.addAttribute("products", figure);
        model.addAttribute("title", "figure");

        return "product";
    }

    @GetMapping("/poster")
    public String productsPoster(Model model, HttpServletResponse response) {

        List<ProductViewModel> poster = this.productService.findByCategory(CategoryEnum.POSTER);
        this.statisticService.saveVisitationInDataBase(response.getHeader("visitor"));

        model.addAttribute("products", poster);
        model.addAttribute("title", "poster");

        return "product";
    }

    @GetMapping("/plush")
    public String productsPlush(Model model, HttpServletResponse response) {

        List<ProductViewModel> plush = this.productService.findByCategory(CategoryEnum.PLUSH);
        this.statisticService.saveVisitationInDataBase(response.getHeader("visitor"));

        model.addAttribute("products", plush);
        model.addAttribute("title", "plush");

        return "product";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") Long id,
                                 Model model) {

        ProductViewModel product = this.productService.getById(id);

        if (product == null) {
            throw new ProductNotFoundException(id);
        }

        model.addAttribute("product", product);
        return "product-detail";
    }
}
