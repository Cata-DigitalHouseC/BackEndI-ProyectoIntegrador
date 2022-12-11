package com.example.Sesion25Paciente.exception;

public class ResourceNotFoundException extends Exception {

        public ResourceNotFoundException(String message) { //Con el constructor le paso el mensaje
            super(message);
        }
}
