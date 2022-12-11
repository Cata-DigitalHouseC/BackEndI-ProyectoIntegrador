package com.example.Sesion25Paciente.controller;

import com.example.Sesion25Paciente.entities.Paciente;
import com.example.Sesion25Paciente.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")

//http:localhost:8080/paciente/5
public class PacienteController {


    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public Paciente registrarPaciente(@RequestBody Paciente paciente){ //RequestBody -> Lo que le envio

        return pacienteService.guardar(paciente);
    }

    @GetMapping("/{id}")
    public Paciente getPaciente(@PathVariable Integer id){
        return pacienteService.buscar(id).get();
    }




}
