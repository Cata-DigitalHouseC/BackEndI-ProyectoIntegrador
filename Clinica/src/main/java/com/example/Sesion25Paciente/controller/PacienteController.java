package com.example.Sesion25Paciente.controller;

import com.example.Sesion25Paciente.dto.PacienteDto;
import com.example.Sesion25Paciente.exception.ResourceNotFoundException;
import com.example.Sesion25Paciente.service.OdontologoService;
import com.example.Sesion25Paciente.service.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")

//http:localhost:8080/paciente/5
public class PacienteController {


    @Autowired //El controller se comunica con el service
    private PacienteService pacienteService;

    //Para Log4j
    private static Logger LOGGER = Logger.getLogger(OdontologoService.class);


    @PostMapping //Agregar o crear un paciente
    public ResponseEntity<Optional<PacienteDto>>registrarPaciente(@RequestBody PacienteDto pacienteDto){ //RequestBody -> Lo que le envio
        LOGGER.info("Ingresando a registrar paciente");
        return ResponseEntity.ok(pacienteService.guardar(pacienteDto));
    }

    @GetMapping("/{id}") //Buscar un paciente por ID
    public ResponseEntity<Optional<PacienteDto>> getPaciente(@PathVariable Integer id) throws ResourceNotFoundException {
        LOGGER.info("Ingresando a buscar paciente por ID "+id);
        return ResponseEntity.ok(pacienteService.buscar(id));
    }

    @PatchMapping("/{id}") //Actualizar un paciente por ID
    public ResponseEntity<Optional<PacienteDto>> actualizarPaciente(@PathVariable Integer id, @RequestBody PacienteDto pacienteDto) throws ResourceNotFoundException {
        LOGGER.info("Ingresando a modificar paciente");
        return ResponseEntity.ok(pacienteService.actualizar(id, pacienteDto));
    }


    @DeleteMapping("/{id}") //Eliminar un paciente por ID
    public ResponseEntity<?> eliminarPaciente(@PathVariable Integer id) throws ResourceNotFoundException {
        pacienteService.eliminar(id);
        LOGGER.info("Ingresando a eliminar paciente");
        return ResponseEntity.ok("Paciente eliminado");
    }

    @GetMapping //listar todos los pacientes
    public ResponseEntity<Collection<PacienteDto>> listarPacientes() throws ResourceNotFoundException {
        LOGGER.info("Ingresando a listar pacientes");
        return ResponseEntity.ok(pacienteService.listar());
    }





}
