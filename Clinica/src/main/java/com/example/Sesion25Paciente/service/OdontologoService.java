package com.example.Sesion25Paciente.service;

import com.example.Sesion25Paciente.dto.OdontologoDto;
import com.example.Sesion25Paciente.entities.Odontologo;
import com.example.Sesion25Paciente.exception.ResourceNotFoundException;
import com.example.Sesion25Paciente.repository.OdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OdontologoService {

    @Autowired
    private OdontologoRepository odontologoRepository; //para acceder a la capa de datos

    @Autowired //transfrma dto a clade negocio, recibe clase de negocio,
    ObjectMapper mapper;

    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    //Para Log4j
    private static Logger LOGGER = Logger.getLogger(OdontologoService.class);

    //: listar, agregar, modificar y eliminar odontólogos.

    //Agregar o guardar odontologo
    public Optional<OdontologoDto> guardar(OdontologoDto odontologoDto) {
        Odontologo odontologoEntity = mapper.convertValue(odontologoDto, Odontologo.class);
        Odontologo odontologoCreadoEntity = odontologoRepository.save(odontologoEntity);
        OdontologoDto odontologoCreadoDto = mapper.convertValue(odontologoCreadoEntity, OdontologoDto.class);
        LOGGER.debug("Guardando al odontologo con ID: "+odontologoCreadoDto.id);
        return Optional.of(odontologoCreadoDto);
    }

    //Listar odontologos
   public Set<OdontologoDto> listar() throws ResourceNotFoundException {

       List<Odontologo> odontologos = odontologoRepository.findAll(); //Devuelve una lista de  odontologos, pero, se requieren dto
       Set<OdontologoDto> odontologosDto = new HashSet<>();
       if(odontologos.isEmpty()){
           throw new ResourceNotFoundException("No se encontraron odontologos");
       }
       for (Odontologo odontologo : odontologos) { //Recorro cada obj odontologo y lo convierot a dto
           OdontologoDto odontologoDto = mapper.convertValue(odontologo, OdontologoDto.class); //cnvierto a dto
           odontologosDto.add(odontologoDto); //adiciono al set d los dto
           LOGGER.debug("Mostando lista de odontólogos de tamaño: "+odontologosDto.size());
       }
       return odontologosDto;
   }

    //listar o buscar un odontologo por id
    public Optional<OdontologoDto> buscar(Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id); //Optional -> tiene o no contenido //Optional envuelve al obj y le agrega mas capacidades
    if (odontologo.isPresent()) {
            OdontologoDto odontologoDto = mapper.convertValue(odontologo.get(), OdontologoDto.class); //mapea de entidad a dto
            LOGGER.debug("Buscando al odontologo con ID: "+odontologoDto.id);
            return Optional.of(odontologoDto);
        } else {
            throw new ResourceNotFoundException("No se encontro el odontologo con id: " + id);
        }
    }

    //Modificar odontologo, si no existe el id -> crea el nuevo odontologo
    public Optional<OdontologoDto> actualizar(Integer id, OdontologoDto odontologoDtoNuevo) throws ResourceNotFoundException {
        if(!buscar(id).isPresent()){
            throw new ResourceNotFoundException("No existe el odontologo con id: " + id);
        }
        Optional<OdontologoDto> odontologoDtoActual = buscar(id);
        if(odontologoDtoActual.isPresent()) {
            Odontologo odontologoEntity = mapper.convertValue(odontologoDtoActual, Odontologo.class);
            odontologoEntity.setNombre(odontologoDtoNuevo.nombre != null ? odontologoDtoNuevo.nombre:odontologoDtoActual.get().nombre);
            odontologoEntity.setApellido(odontologoDtoNuevo.apellido != null ? odontologoDtoNuevo.apellido:odontologoDtoActual.get().apellido);
            odontologoEntity.setMatricula(odontologoDtoNuevo.matricula != null ? odontologoDtoNuevo.matricula:odontologoDtoActual.get().matricula);
            odontologoRepository.save(odontologoEntity);
            OdontologoDto odontologoModificadoDto = mapper.convertValue(odontologoEntity, OdontologoDto.class);
            LOGGER.debug("Modificando odontologo con ID: "+odontologoModificadoDto.id);
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
        LOGGER.debug("Borrando  odontologo con ID: "+id);
        odontologoRepository.deleteById(id);

    }

    /*
    private Odontologo mapDtoToEntity(OdontologoDto odontologo) {
        Odontologo odontologoEntity = new Odontologo();
        odontologoEntity.setMatricula(odontologo.matricula);
        odontologoEntity.setNombre(odontologo.nombre);
        odontologoEntity.setApellido(odontologo.apellido);
        return odontologoEntity;
    }
    */

}
