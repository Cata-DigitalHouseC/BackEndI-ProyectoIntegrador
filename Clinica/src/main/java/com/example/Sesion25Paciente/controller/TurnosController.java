package com.example.Sesion25Paciente.controller;

import com.example.Sesion25Paciente.dto.TurnoDto;

import com.example.Sesion25Paciente.exception.ResourceNotFoundException;
import com.example.Sesion25Paciente.service.OdontologoService;
import com.example.Sesion25Paciente.service.PacienteService;
import com.example.Sesion25Paciente.service.TurnosService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

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

    //Para Log4j
    private static Logger LOGGER = Logger.getLogger(OdontologoService.class);

    @PostMapping
    public ResponseEntity<String> registrarTurno(@RequestBody TurnoDto turnoDto) throws ResourceNotFoundException {
        TurnoDto turnoGuardado = null;
        ResponseEntity<TurnoDto> response;
        if(pacienteService.buscar(turnoDto.paciente.id) == null //Si el paciente no existe o el odontologo no existe
                || odontologoService.buscar(turnoDto.odontologo.id) == null)
        {
            LOGGER.debug("No se pudo registrar el turno");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else if (turnoService.buscarPorIdPaciente(turnoDto.paciente.id) != null) { //Si el paciente ya tiene un turno
            LOGGER.debug("No se pudo registrar el turno");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else //Crear turno exitosamente
        {
            LOGGER.info("Registrando turno para el paciente con ID: "+turnoDto.paciente.id);
            return  ResponseEntity.ok(turnoService.guardar(turnoDto));
        }
    }

    @GetMapping
    public ResponseEntity<Collection<TurnoDto>> listarTurnos() throws ResourceNotFoundException {
        Set<TurnoDto> turnosDto = turnoService.listar();

        if(turnosDto.isEmpty())
        {
            LOGGER.debug("No se encontraron turnos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(turnoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarTurno(@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<TurnoDto> turnoDto = turnoService.buscarPorIdPaciente(id);
        LOGGER.info("Buscando turno para el paciente con ID: "+id);
        return ResponseEntity.ok(turnoDto.get());
    }

}
