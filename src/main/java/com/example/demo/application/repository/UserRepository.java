package com.example.demo.application.repository;

import com.example.demo.commons.dto.UserDto;
import com.example.demo.domain.User;

public interface UserRepository {
    UserDto save(User user);
}

