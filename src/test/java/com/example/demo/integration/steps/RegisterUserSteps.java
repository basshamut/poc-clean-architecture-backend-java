package com.example.demo.integration.steps;

import com.example.demo.commons.dto.UserDto;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterUserSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private ResponseEntity<UserDto> response;

    @When("registro un usuario con nombre {string}")
    public void registro_un_usuario_con_nombre(String nombre) {
        String url = "http://localhost:" + port + "/users?name=" + nombre;
        response = restTemplate.postForEntity(url, null, UserDto.class);
    }

    @Then("el sistema devuelve un usuario con nombre {string}")
    public void el_sistema_devuelve_un_usuario_con_nombre(String nombre) {
        assertNotNull(response.getBody());
        assertEquals(nombre, response.getBody().name());
    }
}
