package com.example.demo.application.usecase;

import com.example.demo.commons.dto.UserDto;

public interface RegisterUserUseCase {
    UserDto register(String name);
}
