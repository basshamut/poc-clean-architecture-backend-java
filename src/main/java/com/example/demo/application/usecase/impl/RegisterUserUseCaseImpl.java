package com.example.demo.application.usecase.impl;

import com.example.demo.application.repository.UserRepository;
import com.example.demo.application.usecase.RegisterUserUseCase;
import com.example.demo.commons.dto.UserDto;
import com.example.demo.domain.User;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepository userRepository;

    public RegisterUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto register(String name) {
        User newUser = new User(null, name);
        return userRepository.save(newUser);
    }
}

