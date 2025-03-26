package com.example.demo.adapters.output.persistance;

import com.example.demo.adapters.output.persistance.jpa.JpaUserRepository;
import com.example.demo.application.repository.UserRepository;
import com.example.demo.commons.dto.UserDto;
import com.example.demo.commons.mapper.UserMapper;
import com.example.demo.domain.User;
import org.springframework.stereotype.Service;

@Service
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

