package bg.softuni.onlineshop.config.mapping;

import bg.softuni.onlineshop.model.dto.UserLoginDTO;
import bg.softuni.onlineshop.model.dto.UserRegisterDTO;
import bg.softuni.onlineshop.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserEntity userRegisterDTOtoUser(UserRegisterDTO userDto);

    UserEntity userLoginDTOToUser(UserLoginDTO userDTO);
}
