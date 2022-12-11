package com.example.Sesion25Paciente.service;

import com.example.Sesion25Paciente.dto.OdontologoDto;
import com.example.Sesion25Paciente.dto.PacienteDto;
import com.example.Sesion25Paciente.dto.TurnoDto;
import com.example.Sesion25Paciente.entities.Turno;
import com.example.Sesion25Paciente.exception.ResourceNotFoundException;
import com.example.Sesion25Paciente.repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TurnosService {

    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    ObjectMapper mapper;
    public TurnosService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }


    public TurnoDto guardar(TurnoDto turnoDto){
        //paciente ID
        //odontologo ID
        //fecha
        PacienteDto pacienteDto = pacienteService.buscar(turnoDto.pacienteId).get();
        OdontologoDto odontologoDto = odontologoService.buscar(turnoDto.odontologoId).get();
        TurnoDto turnoCreadoDto = new TurnoDto(turnoDto.pacienteId,turnoDto.odontologoId,turnoDto.fecha);

        //dto a entity para guardar en el repositorio
        Turno turnoEntity = mapper.convertValue(turnoDto, Turno.class);
        //llamo al repositorio, para guardar el turno
        Turno entity = turnoRepository.save(turnoEntity);

        //Retorno el turno creado dto
        return turnoCreadoDto;
    }

    private Optional<TurnoDto> buscarPorIdPaciente(Integer id) throws ResourceNotFoundException {

        Turno turnoEncontrado = null;
        for (Turno turno : turnoRepository.findAll()) {
            if (turno.getPaciente().getId().equals(id)) {
                return Optional.of(mapper.convertValue(turno, TurnoDto.class));
            } else {
                throw new ResourceNotFoundException("No se encontro turno, para el paciente con el id: " + id);
            }
        }
        return null;
    }



    public Set<TurnoDto> listar() {

        List<Turno> turnos= turnoRepository.findAll();
        Set<TurnoDto> turnosDto = new HashSet<>();
        for (Turno turno : turnos) {
            turnosDto.add(mapper.convertValue(turno, TurnoDto.class));
        }
        return turnosDto;
    }
}
