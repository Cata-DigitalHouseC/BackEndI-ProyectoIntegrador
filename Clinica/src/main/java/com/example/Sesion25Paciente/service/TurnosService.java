package com.example.Sesion25Paciente.service;

import com.example.Sesion25Paciente.dto.TurnoDto;
import com.example.Sesion25Paciente.entities.Paciente;
import com.example.Sesion25Paciente.entities.Turno;
import com.example.Sesion25Paciente.repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnosService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    ObjectMapper mapper;
    public TurnosService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }


    public TurnoDto guardar(TurnoDto turnoDto){
        //paciente ID
        //odontologo ID
        //fecha
        //Convierte el DTO a Entity
        Turno turnoEntity = mapper.convertValue(turnoDto, Turno.class);
        Paciente paciente = pacienteService.buscar(turnoDto.pacienteId).get();
        Turno turnoEntity2 = new Turno(null,paciente, null, turnoDto.fecha);
        //llamo al repositorio
        Turno entity = turnoRepository.save(turnoEntity2);
        //Convierto el Entity a DTO, para mandarlo al Controller
        TurnoDto turnoCreadoDto = mapper.convertValue(entity, TurnoDto.class);

        return turnoCreadoDto;
    }

    private Turno buscarPorIdPaciente(Integer id) {

        return null;

        /*
        Turno turnoEncontrado=null;
        for (Turno turno : turnoRepository.findAll()) {
            if(turno.getPaciente().getId().equals(id)){
                return turno;
            }
        }
        return null;
         */
    }


    public List<Turno> listar() {
        return turnoRepository.findAll();
    }
}
