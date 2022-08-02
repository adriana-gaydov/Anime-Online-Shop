package bg.softuni.onlineshop.config.mapping;

import bg.softuni.onlineshop.model.entity.IpStatsEntity;
import bg.softuni.onlineshop.model.view.IpStatsViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IpStatsMapper {

    StatisticMapper INSTANCE = Mappers.getMapper(StatisticMapper.class);

    @Mapping(target = "id", source = "id")
    IpStatsViewModel entityToViewModel(IpStatsEntity product);



}