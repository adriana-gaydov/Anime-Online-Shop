package bg.softuni.onlineshop.service.impl;

import bg.softuni.onlineshop.config.mapping.ProductMapper;
import bg.softuni.onlineshop.model.dto.ProductAddDTO;
import bg.softuni.onlineshop.model.dto.ProductEditDTO;
import bg.softuni.onlineshop.model.dto.SearchDTO;
import bg.softuni.onlineshop.model.entity.ProductEntity;
import bg.softuni.onlineshop.model.enums.CategoryEnum;
import bg.softuni.onlineshop.model.view.ProductViewModel;
import bg.softuni.onlineshop.repository.ProductRepository;
import bg.softuni.onlineshop.repository.ProductSpecification;
import bg.softuni.onlineshop.service.CategoryService;
import bg.softuni.onlineshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<ProductViewModel> getAllProducts() {

        return this.productRepository.findAll()
                .stream().map(ProductMapper.INSTANCE::productToView)
                .toList();
    }

    @Override
    public Optional<ProductEntity> findById(Long productId) {
        return this.productRepository.findById(productId);
    }

    @Override
    public void initProducts() {

        if (this.productRepository.count() != 0) {
            return;
        }

        for (int i = 0; i < 6; i++) {

            ProductEntity product = new ProductEntity()
                    .setCategory(this.categoryService.findByCategory(CategoryEnum.CLOTHING))
                    .setDescription("random")
                    .setName("test" + i)
                    .setPrice(new BigDecimal("23"))
                    .setQuantity(21L)
                    .setImageUrl("https://res.cloudinary.com/adrianapersonalprj/image/upload/v1657701517/rcoon/products/1_rnqtdw.jpg")
                    .setLastUpdate(LocalDateTime.now());

            this.productRepository.save(product);
        }
    }

    @Override
    public void save(ProductEntity product) {
        this.productRepository.save(product);
        this.productRepository.flush();
    }

    @Override
    public List<ProductViewModel> findByCategory(CategoryEnum category) {

        return this.productRepository.findByCategoryCategory(category)
                .stream().map(ProductMapper.INSTANCE::productToView)
                .toList();
    }

    @Override
    public boolean isProductExists(String name) {
        return this.productRepository.findByName(name).isPresent();
    }

    @Override
    public void addProduct(ProductAddDTO productModel, String imageUrl) {

        ProductEntity product = ProductMapper.INSTANCE.dtoToProduct(productModel);

        product.setImageUrl(imageUrl);
        product.setCategory(this.categoryService.findByCategory(productModel.getCategory()));
        product.setLastUpdate(LocalDateTime.now());

        this.productRepository.save(product);
    }

    @Override
    public ProductEntity updateProduct(ProductEntity product, ProductEditDTO productEditModel) {

        product.setName(productEditModel.getName());
        product.setDescription(productEditModel.getDescription());
        product.setQuantity(productEditModel.getQuantity());
        product.setPrice(productEditModel.getPrice());

        product.setCategory(this.categoryService.findByCategory(productEditModel.getCategory()));
        product.setLastUpdate(LocalDateTime.now());
        product.setImageUrl(productEditModel.getImageUrl());

        return this.productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public ProductViewModel getById(Long id) {
        return this.productRepository.findById(id).map(ProductMapper.INSTANCE::productToView)
                .orElse(null);
    }

    @Override
    public List<ProductViewModel> getNewestDeals() {

        return this.productRepository.findAll().stream().sorted(Comparator.comparing(ProductEntity::getLastUpdate).reversed())
                .limit(5)
                .map(ProductMapper.INSTANCE::productToView)
                .toList();
    }

    @Override
    public List<ProductViewModel> searchOffer(SearchDTO searchDTO) {

        return this.productRepository.findAll(new ProductSpecification(searchDTO))
                .stream().map(ProductMapper.INSTANCE::productToView)
                .toList();
    }

    @Override
    public List<ProductViewModel> getIndexProducts() {

        Random random = new Random();
        List<ProductViewModel> result = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            long randomIndex = random.nextLong(this.productRepository.count());

            result.add(ProductMapper.INSTANCE.productToView(this.productRepository.getById(randomIndex + 1)));
        }

        return result;
    }
}
