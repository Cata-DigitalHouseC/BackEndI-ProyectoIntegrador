/*
package com.example.Sesion25Paciente.service;

import com.example.Sesion25Paciente.entities.Odontologo;
import com.example.Sesion25Paciente.entities.Paciente;
import com.example.Sesion25Paciente.entities.Turno;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TurnosServiceTest {

    @Test
    void verificar_no_guardar_turno_duplicado() {
        //RECLA DE NEGOCIO: No se puede asignar mas de un turno por paciente
        //Si no puede guardar devuelve nulo

        //Arrange
        TurnosRepositoryImitador turnosRepositoryImitador = new TurnosRepositoryImitador();
        TurnosService turnosService = new TurnosService(turnosRepositoryImitador);
        Turno turno = new Turno(1, new Paciente(1,"",""),
                new Odontologo(1,"","",1),
                new Date());
        //Act
        Turno result  = turnosService.guardar(turno);
        //Assert
        assertNull(result);
    }

    @Test
    void verificar_pueda_guardar_turno_no_duplicado() {
        //REGLA DE NEGOCIO: No se puede asignar mas de un turno por paciente
        //Si no puede guardar devuelve nulo
        //Arrange
        TurnosRepositoryImitador turnosRepositoryImitador = new TurnosRepositoryImitador();
        TurnosService turnosService = new TurnosService(turnosRepositoryImitador);
        Turno turno = new Turno(1, new Paciente(3,"",""),
                new Odontologo(1,"","",1),
                new Date());
        //Act
        Turno result  = turnosService.guardar(turno);
        //Assert
        assertNotNull(result);
    }
}

 */