package com.example.Sesion25Paciente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST) //Error 400 BAD_REQUEST
public class ResourceNotFoundException extends Exception {

        public ResourceNotFoundException(String message) { //Con el constructor le paso el mensaje

            super(message);
        }
}
