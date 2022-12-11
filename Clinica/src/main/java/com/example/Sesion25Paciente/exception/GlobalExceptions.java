package com.example.Sesion25Paciente.exception;


import com.example.Sesion25Paciente.GlobalExceptionHandler;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptions{

    @ExceptionHandler ({ResourceNotFoundException.class})
    public ResponseEntity<String> procesoExceptionSQL(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler({SQLException.class})
    public ResponseEntity<String> procesoExceptionBadRequest(SQLException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

}

