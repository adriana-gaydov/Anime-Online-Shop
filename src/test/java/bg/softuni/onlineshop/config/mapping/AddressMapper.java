package bg.softuni.onlineshop.config.mapping;

import bg.softuni.onlineshop.model.dto.AddressDTO;
import bg.softuni.onlineshop.model.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressEntity addressDTOtoAddressEntity(AddressDTO addressDTO);
}
