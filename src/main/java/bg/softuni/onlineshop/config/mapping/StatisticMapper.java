package bg.softuni.onlineshop.config.mapping;

import bg.softuni.onlineshop.model.cart.CartItem;
import bg.softuni.onlineshop.model.entity.ProductEntity;
import bg.softuni.onlineshop.model.entity.StatisticEntity;
import bg.softuni.onlineshop.model.view.ProductViewModel;
import bg.softuni.onlineshop.model.view.StatisticViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StatisticMapper {

    StatisticMapper INSTANCE = Mappers.getMapper(StatisticMapper.class);

    @Mapping(target = "id", source = "id")
    StatisticViewModel entityToViewModel(StatisticEntity product);



}