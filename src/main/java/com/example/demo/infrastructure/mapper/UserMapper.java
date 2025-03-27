package com.example.demo.infrastructure.mapper;

import com.example.demo.adapters.output.persistance.entity.UserEntity;
import com.example.demo.adapters.input.web.dto.UserDto;
import com.example.demo.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserDto fromDomainToDto(User user);

    UserEntity fromDomainToEntity(User user);

    User fromDtoToDomain(UserDto userDTO);

    UserEntity fromDtoToEntity(UserDto userDTO);

    UserDto fromEntityToDto(UserEntity userEntity);
}
