package bg.softuni.onlineshop.service;

import bg.softuni.onlineshop.model.dto.ProductAddDTO;
import bg.softuni.onlineshop.model.dto.ProductEditDTO;
import bg.softuni.onlineshop.model.dto.SearchDTO;
import bg.softuni.onlineshop.model.entity.ProductEntity;
import bg.softuni.onlineshop.model.enums.CategoryEnum;
import bg.softuni.onlineshop.model.view.ProductViewModel;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductViewModel> getAllProducts();

    Optional<ProductEntity> findById(Long productId);

    void initProducts();

    void save(ProductEntity product);

    List<ProductViewModel> findByCategory(CategoryEnum clothing);

    boolean isProductExists(String name);

    ProductEntity addProduct(ProductAddDTO productModel, String imageUrl);

    ProductEntity updateProduct(ProductEntity product, ProductEditDTO productEditModel);

    void deleteById(Long id);

    ProductViewModel getById(Long id);

    List<ProductViewModel> getNewestDeals();

    List<ProductViewModel> searchOffer(SearchDTO searchDTO);

    List<ProductViewModel> getIndexProducts();
}
