package com.example.demo.application.service;

import com.example.demo.application.ports.output.UserRepository;
import com.example.demo.application.ports.input.RegisterUserUseCase;
import com.example.demo.adapters.input.web.dto.UserDto;
import com.example.demo.domain.User;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepository userRepository;

    public RegisterUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto register(String name) {
        User newUser = new User(null, name);
        return userRepository.save(newUser);
    }
}

