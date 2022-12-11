package com.example.Sesion25Paciente.controller;

import com.example.Sesion25Paciente.dto.PacienteDto;
import com.example.Sesion25Paciente.exception.ResourceNotFoundException;
import com.example.Sesion25Paciente.service.PacienteService;
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


    @PostMapping //Agregar o crear un paciente
    public ResponseEntity<Optional<PacienteDto>>registrarPaciente(@RequestBody PacienteDto pacienteDto){ //RequestBody -> Lo que le envio
        return ResponseEntity.ok(pacienteService.guardar(pacienteDto));
    }

    @GetMapping("/{id}") //Buscar un paciente por ID
    public ResponseEntity<Optional<PacienteDto>> getPaciente(@PathVariable Integer id){
        return ResponseEntity.ok(pacienteService.buscar(id));
    }

    @PatchMapping("/{id}") //Actualizar un paciente por ID
    public ResponseEntity<Optional<PacienteDto>> actualizarPaciente(@PathVariable Integer id, @RequestBody PacienteDto pacienteDto){
        return ResponseEntity.ok(pacienteService.actualizar(id, pacienteDto));
    }


    @DeleteMapping("/{id}") //Eliminar un paciente por ID
    public ResponseEntity<?> eliminarPaciente(@PathVariable Integer id) throws ResourceNotFoundException {
        pacienteService.eliminar(id);
        return ResponseEntity.ok("Paciente eliminado");
    }

    @GetMapping //listar todos los pacientes
    public ResponseEntity<Collection<PacienteDto>> listarPacientes(){
        return ResponseEntity.ok(pacienteService.listar());
    }





}
