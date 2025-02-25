package com.santosguilherme.register.controller.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private Long timestamp;
    private HttpStatus status;
    private String message;
    private String path;
    private List<FieldMessage> errors;
}
