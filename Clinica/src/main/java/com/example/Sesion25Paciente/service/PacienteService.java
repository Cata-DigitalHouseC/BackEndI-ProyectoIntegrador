package com.example.Sesion25Paciente.service;

import com.example.Sesion25Paciente.dto.OdontologoDto;
import com.example.Sesion25Paciente.entities.Odontologo;
import com.example.Sesion25Paciente.exception.ResourceNotFoundException;
import com.example.Sesion25Paciente.repository.OdontologoRepository;
import com.example.Sesion25Paciente.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PacienteService{

    @Autowired
    private PacienteRepository pacienteRepository; //para acceder a la capa de datos

    @Autowired //transfrma dto a clade negocio, recibe clase de negocio,
    ObjectMapper mapper;
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    //: listar, agregar, modificar y eliminar odontólogos.

    //Agregar o guardar odontologo
    public Optional<OdontologoDto> guardar(OdontologoDto odontologoDto) {
        Odontologo odontologoEntity = mapper.convertValue(odontologoDto, Odontologo.class);
        Odontologo odontologoCreadoEntity = pacienteRepository.save(odontologoEntity);
        OdontologoDto odontologoCreadoDto = mapper.convertValue(odontologoCreadoEntity, OdontologoDto.class);
        return Optional.of(odontologoCreadoDto);
    }

    //Listar odontologos
    public Set<OdontologoDto> listar() {

        List<Odontologo> odontologos = pacienteRepository.findAll(); //Devuelve una lista de  odontologos, pero, se requieren dto
        Set<OdontologoDto> odontologosDto = new HashSet<>();
        for (Odontologo odontologo : odontologos) { //Recorro cada obj odontologo y lo convierot a dto
            OdontologoDto odontologoDto = mapper.convertValue(odontologo, OdontologoDto.class); //cnvierto a dto
            odontologosDto.add(odontologoDto); //adiciono al set d los dto
        }
        return odontologosDto;
    }

    //listar o buscar un odontologo por id
    public Optional<OdontologoDto> buscar(Integer id) {
        Optional<Odontologo> odontologo = pacienteRepository.findById(id); //Optional -> tiene o no contenido //Optional envuelve al obj y le agrega mas capacidades
        if (odontologo.isPresent()) {
            OdontologoDto odontologoDto = mapper.convertValue(odontologo.get(), OdontologoDto.class); //mapea de entidad a dto
            return Optional.of(odontologoDto);
        } else {
            return Optional.empty(); //si no encuentra nada
        }
    }

    //Modificar odontologo, si no existe el id -> crea el nuevo odontologo
    public Optional<OdontologoDto> actualizar(Integer id, OdontologoDto odontologoDtoNuevo) {
        Optional<OdontologoDto> odontologoDtoActual = buscar(id);
        if(odontologoDtoActual.isPresent()) {
            Odontologo odontologoEntity = mapper.convertValue(odontologoDtoActual, Odontologo.class);
            odontologoEntity.setNombre(odontologoDtoNuevo.nombre != null ? odontologoDtoNuevo.nombre:odontologoDtoActual.get().nombre);
            odontologoEntity.setApellido(odontologoDtoNuevo.apellido != null ? odontologoDtoNuevo.apellido:odontologoDtoActual.get().apellido);
            odontologoEntity.setMatricula(odontologoDtoNuevo.matricula != null ? odontologoDtoNuevo.matricula:odontologoDtoActual.get().matricula);
            pacienteRepository.save(odontologoEntity);
            OdontologoDto odontologoModificadoDto = mapper.convertValue(odontologoEntity, OdontologoDto.class);
            return Optional.of(odontologoModificadoDto);
        } else {
            return guardar(odontologoDtoNuevo);
            //throw new ResourceNotFoundException("No se encontro el odontologo con el id: " + id);
        }
    }

    public void eliminar(Integer id) throws ResourceNotFoundException {

        if(!buscar(id).isPresent()){
            throw new ResourceNotFoundException("No se encontro el odontologo con el id: " + id); //o SQLException
        }
        pacienteRepository.deleteById(id);

    }

    private Odontologo mapDtoToEntity(OdontologoDto odontologo) {
        Odontologo odontologoEntity = new Odontologo();
        odontologoEntity.setMatricula(odontologo.matricula);
        odontologoEntity.setNombre(odontologo.nombre);
        odontologoEntity.setApellido(odontologo.apellido);
        return odontologoEntity;
    }

}
