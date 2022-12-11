package com.example.Sesion25Paciente.service;

import com.example.Sesion25Paciente.dto.PacienteDto;
import com.example.Sesion25Paciente.entities.Paciente;
import com.example.Sesion25Paciente.exception.ResourceNotFoundException;
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

    //: listar, agregar, modificar y eliminar pacientes

    //Agregar o guardar paciente
    public Optional<PacienteDto> guardar(PacienteDto pacienteDto) {
        Paciente pacienteEntity = mapper.convertValue(pacienteDto, Paciente.class);
        Paciente pacienteCreadoEntity = pacienteRepository.save(pacienteEntity);
        PacienteDto pacienteCreadoDto = mapper.convertValue(pacienteCreadoEntity, PacienteDto.class);
        return Optional.of(pacienteCreadoDto);
    }

    //Listar pacientes
    public Set<PacienteDto> listar() {

        List<Paciente> pacientes = pacienteRepository.findAll(); //Devuelve una lista de pacientes, pero, se requieren dto
        Set<PacienteDto> pacientesDto = new HashSet<>();
        for (Paciente paciente : pacientes) { //Recorro cada obj pacientes y lo convierot a dto
            PacienteDto pacienteDto = mapper.convertValue(paciente, PacienteDto.class); //cnvierto a dto
            pacientesDto.add(pacienteDto); //adiciono al set d los dto
        }
        return pacientesDto;
    }

    //listar o buscar un Paciente por id
    public Optional<PacienteDto> buscar(Integer id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id); //Optional -> tiene o no contenido //Optional envuelve al obj y le agrega mas capacidades
        if (paciente.isPresent()) {
            PacienteDto pacienteDto = mapper.convertValue(paciente.get(), PacienteDto.class); //mapea de entidad a dto
            return Optional.of(pacienteDto);
        } else {
            return Optional.empty(); //si no encuentra nada
        }
    }

    //Modificar Paciente, si no existe el id -> crea el nuevo Paciente
    public Optional<PacienteDto> actualizar(Integer id, PacienteDto pacienteDtoNuevo) {
        Optional<PacienteDto> pacienteDtoActual = buscar(id);
        if(pacienteDtoActual.isPresent()) { //si existe el paciente, lo actualizo
            Paciente pacienteEntity = mapper.convertValue(pacienteDtoActual, Paciente.class);
            pacienteEntity.setNombre(pacienteDtoNuevo.nombre != null ? pacienteDtoNuevo.nombre:pacienteDtoActual.get().nombre);
            pacienteEntity.setApellido(pacienteDtoNuevo.apellido != null ? pacienteDtoNuevo.apellido:pacienteDtoActual.get().apellido);
            pacienteEntity.setDomicilio(pacienteDtoNuevo.domicilio != null ? pacienteDtoNuevo.domicilio:pacienteDtoActual.get().domicilio);
            pacienteEntity.setDni(pacienteDtoNuevo.dni != null ? pacienteDtoNuevo.dni:pacienteDtoActual.get().dni);
            pacienteEntity.setFechaAlta(pacienteDtoNuevo.fechaAlta != null ? pacienteDtoNuevo.fechaAlta:pacienteDtoActual.get().fechaAlta);
            pacienteRepository.save(pacienteEntity);
            PacienteDto pacienteModificadoDto = mapper.convertValue(pacienteEntity, PacienteDto.class);
            return Optional.of(pacienteModificadoDto);
        } else { //si no existe el paciente, lo creo
            return guardar(pacienteDtoNuevo);
            //throw new ResourceNotFoundException("No se encontro el paciente con el id: " + id);
        }
    }

    public void eliminar(Integer id) throws ResourceNotFoundException {

        if(!buscar(id).isPresent()){
            throw new ResourceNotFoundException("No se encontro el paciente con el id: " + id); //o SQLException
        }
        pacienteRepository.deleteById(id);

    }

}
