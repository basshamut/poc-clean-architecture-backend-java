package com.example.demo.unit;

import com.example.demo.adapters.output.persistance.UserRepositoryImpl;
import com.example.demo.adapters.output.persistance.entity.UserEntity;
import com.example.demo.adapters.output.persistance.jpa.JpaUserRepository;
import com.example.demo.adapters.input.web.dto.UserDto;
import com.example.demo.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class UserRepositoryImplTest {

    @InjectMocks
    private UserRepositoryImpl userRepository;

    @Mock
    private JpaUserRepository jpaUserRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldRegisterUserCorrectly() {
        String name = "::name::";
        User user = new User(1L, name);
        UserEntity userEntity = new UserEntity(1L, name);

        when(jpaUserRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserDto result = userRepository.save(user);

        assertNotNull(result);
        assertEquals(name, result.name());
        assertEquals(1L, result.id());
    }
}
