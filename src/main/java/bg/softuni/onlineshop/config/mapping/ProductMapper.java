package bg.softuni.onlineshop.config.mapping;

import bg.softuni.onlineshop.model.cart.CartItem;
import bg.softuni.onlineshop.model.dto.CartItemDTO;
import bg.softuni.onlineshop.model.dto.ProductAddDTO;
import bg.softuni.onlineshop.model.dto.ProductEditDTO;
import bg.softuni.onlineshop.model.entity.ProductEntity;
import bg.softuni.onlineshop.model.view.ProductViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", source = "id")
    ProductViewModel productToView(ProductEntity product);

    @Mappings(@Mapping(target = "id", source = "id"))
    CartItem productToCartItem(ProductEntity product);


    ProductEntity dtoToProduct(ProductAddDTO productAddDTO);
    ProductEntity dtoEditToProduct(ProductEditDTO productAddDTO);


}
