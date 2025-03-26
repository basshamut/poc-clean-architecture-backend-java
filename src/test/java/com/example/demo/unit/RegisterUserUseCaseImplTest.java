package com.example.demo.unit;

import com.example.demo.application.repository.UserRepository;
import com.example.demo.application.usecase.impl.RegisterUserUseCaseImpl;
import com.example.demo.commons.dto.UserDto;
import com.example.demo.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegisterUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RegisterUserUseCaseImpl registerUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldRegisterUserViaRepository() {
        // Arrange
        String name = "Laura";
        UserDto expectedDto = new UserDto(1L, name);

        when(userRepository.save(any(User.class))).thenReturn(expectedDto);

        // Act
        UserDto result = registerUserUseCase.register(name);

        // Assert
        assertNotNull(result);
        assertEquals(expectedDto.id(), result.id());
        assertEquals(expectedDto.name(), result.name());
        verify(userRepository).save(any(User.class));
    }

}
