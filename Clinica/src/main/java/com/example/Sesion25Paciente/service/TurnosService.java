package com.example.Sesion25Paciente.service;

import com.example.Sesion25Paciente.dto.OdontologoDto;
import com.example.Sesion25Paciente.dto.PacienteDto;
import com.example.Sesion25Paciente.dto.TurnoDto;
import com.example.Sesion25Paciente.entities.Turno;
import com.example.Sesion25Paciente.exception.ResourceNotFoundException;
import com.example.Sesion25Paciente.repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
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

    //Para Log4j
    private static Logger LOGGER = Logger.getLogger(OdontologoService.class);


    public String guardar(TurnoDto turnoDto) throws ResourceNotFoundException {
        //paciente ID
        //odontologo ID
        //fecha
        PacienteDto pacienteDto = pacienteService.buscar(turnoDto.paciente.id).get();
        OdontologoDto odontologoDto = odontologoService.buscar(turnoDto.odontologo.id).get();
        TurnoDto turnoCreadoDto = new TurnoDto(turnoDto.paciente,turnoDto.odontologo,turnoDto.date);

        //dto a entity para guardar en el repositorio
        Turno turnoEntity = mapper.convertValue(turnoDto, Turno.class);
        //llamo al repositorio, para guardar el turno
        Turno entity = turnoRepository.save(turnoEntity);

        LOGGER.debug("Guardando al turno con ID: "+turnoCreadoDto.id);

        //Retorno el turno creado dto
        return "Turno creado para la fecha "+turnoCreadoDto.date +" con el paciente "+turnoCreadoDto.paciente.id+" y el odontologo "+turnoCreadoDto.odontologo.id;
    }

    public Optional<TurnoDto> buscarPorIdPaciente(Integer id) throws ResourceNotFoundException {

        Turno turnoEncontrado = null;
        for (Turno turno : turnoRepository.findAll()) {
            if (turno.getPaciente().getId().equals(id)) {
                LOGGER.debug("Buscando el turno con paciente ID: "+turno.getPaciente().getId());
                return Optional.of(mapper.convertValue(turno, TurnoDto.class));
            } else {
                throw new ResourceNotFoundException("No se encontro turno, para el paciente con el id: " + id);
            }
        }
        return null;
    }

    public Set<TurnoDto> listar() throws ResourceNotFoundException {

        List<Turno> turnos= turnoRepository.findAll();
        Set<TurnoDto> turnosDto = new HashSet<>();
        if(turnos.isEmpty()){
            throw new ResourceNotFoundException("No se encontraron turnos");
        }
        for (Turno turno : turnos) {
            turnosDto.add(mapper.convertValue(turno, TurnoDto.class));
            LOGGER.debug("Mostrando lista de turnos de tamaño: "+turnosDto.size());
        }
        return turnosDto;
    }
}
