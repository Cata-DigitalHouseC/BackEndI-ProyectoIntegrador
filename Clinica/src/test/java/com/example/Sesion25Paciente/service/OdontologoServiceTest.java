package com.example.Sesion25Paciente.service;

import com.example.Sesion25Paciente.dto.OdontologoDto;
import com.example.Sesion25Paciente.entities.Odontologo;
import com.example.Sesion25Paciente.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    void guardar() {
        //Arrange -> Preparar el escenario, crear objetos
        OdontologoDto dto = new OdontologoDto("Juan","Perez",1234);
        //Act
        OdontologoDto result = odontologoService.guardar(dto);
        //Assert
        assertNotNull(result);

    }

    @Test
    void eliminar() throws ResourceNotFoundException {
        //Arrange -> Preparar el escenario, crear objetos
        OdontologoDto dto = new OdontologoDto("Juan","Perez",1234);
        OdontologoDto odontologoGuardado = odontologoService.guardar(dto);
        //Act
        odontologoService.eliminar(odontologoGuardado.id);
        //Assert
        Optional<Odontologo> result = odontologoService.buscar(odontologoGuardado.id);
        assertFalse(result.isPresent());
    }
}