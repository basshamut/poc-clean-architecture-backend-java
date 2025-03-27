package com.example.demo.adapters.output.persistance;

import com.example.demo.adapters.input.web.dto.UserDto;
import com.example.demo.adapters.output.persistance.jpa.JpaUserRepository;
import com.example.demo.application.ports.output.UserRepository;
import com.example.demo.domain.User;
import com.example.demo.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public UserDto save(User user) {
        var entity = UserMapper.MAPPER.fromDomainToEntity(user);
        var saved = jpaUserRepository.save(entity);
        return UserMapper.MAPPER.fromEntityToDto(saved);
    }
}

