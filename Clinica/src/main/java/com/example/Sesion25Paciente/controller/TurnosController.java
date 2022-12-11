package com.example.Sesion25Paciente.controller;

import com.example.Sesion25Paciente.dto.TurnoDto;
import com.example.Sesion25Paciente.entities.Turno;
import com.example.Sesion25Paciente.service.OdontologoService;
import com.example.Sesion25Paciente.service.PacienteService;
import com.example.Sesion25Paciente.service.TurnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnosController
{
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private TurnosService turnoService;
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<TurnoDto> registrarTurno(@RequestBody TurnoDto turno)
    {
        Turno turnoGuardado = null;
        ResponseEntity<TurnoDto> response;

        if(pacienteService.buscar(turno.pacienteId) == null
                && odontologoService.buscar(turno.odontologoId) == null)

        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else
        {
            response = ResponseEntity.ok(turnoService.guardar(turno));
        }

        return response;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos()
    {
        List<Turno> turnos = turnoService.listar();

        if(turnos.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(turnoService.listar());
    }

}
