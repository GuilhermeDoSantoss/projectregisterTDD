package com.santosguilherme.register.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santosguilherme.register.controller.request.UserRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @CsvSource({"Jo,12", "Guilherme dos Santos,12345678"})
    public void should_return_username_length_error(String name, String password) throws Exception {
        var request = new UserRequest(name, password, null);
        mockmvc.perform(
                post("/api/v1/user")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(3)))
        .andExpect(jsonPath("$.errors[?(@.fieldName == 'name')].message",
                contains("O tamanho deve ser entre 3 e 10")))
        .andExpect(jsonPath("$.errors[?(@.fieldName == 'password')].message",
                contains("O tamanho deve ser entre 4 e 6")))
        .andExpect(jsonPath("$.errors[?(@.fieldName == 'dateOfBirth')].message",
                contains("O campo de data não pode ser nulo")));
    }
}
