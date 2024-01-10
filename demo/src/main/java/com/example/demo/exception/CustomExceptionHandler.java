package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Log the exception
        e.printStackTrace();

        // Devuelve una respuesta HTTP 500 con un mensaje personalizado
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Se produjo un error interno en el servidor. Por favor, intenta de nuevo.");
    }
}