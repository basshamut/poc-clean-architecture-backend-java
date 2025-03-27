package com.example.demo.adapters.input.web;

import com.example.demo.application.ports.input.RegisterUserUseCase;
import com.example.demo.adapters.input.web.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final RegisterUserUseCase registerUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping
    public UserDto register(@RequestParam String name) {
        return registerUserUseCase.register(name);
    }
}
