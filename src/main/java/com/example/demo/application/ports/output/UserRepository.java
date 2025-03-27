package com.example.demo.application.ports.output;

import com.example.demo.adapters.input.web.dto.UserDto;
import com.example.demo.domain.User;

public interface UserRepository {
    UserDto save(User user);
}

