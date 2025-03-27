package com.example.demo.application.ports.input;

import com.example.demo.adapters.input.web.dto.UserDto;

public interface RegisterUserUseCase {
    UserDto register(String name);
}
