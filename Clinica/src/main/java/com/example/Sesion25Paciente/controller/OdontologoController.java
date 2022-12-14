package com.example.Sesion25Paciente.controller;

import com.example.Sesion25Paciente.dto.OdontologoDto;
import com.example.Sesion25Paciente.dto.PacienteDto;
import com.example.Sesion25Paciente.exception.ResourceNotFoundException;
import com.example.Sesion25Paciente.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController
{
    @Autowired
    private OdontologoService odontologoService;

    //Para Log4j
    private static Logger LOGGER = Logger.getLogger(OdontologoService.class);

    @PostMapping
    public ResponseEntity<Optional<OdontologoDto>> registrarOdontologo(@Valid @RequestBody OdontologoDto odontologoDto)
    {
        LOGGER.info("Ingresando a registrar odontologo");
        return ResponseEntity.status(HttpStatus.CREATED).body(odontologoService.guardar(odontologoDto));
    }


    @GetMapping
    public ResponseEntity<Collection<OdontologoDto>> listarOdontologos() throws ResourceNotFoundException {
        LOGGER.info("Ingresando a listar odontologos");
        return ResponseEntity.ok(odontologoService.listar());
    }

    @GetMapping("/{id}") //Buscar un odontologo por ID
    public ResponseEntity<Optional<OdontologoDto>> getOdontolog(@PathVariable Integer id) throws ResourceNotFoundException {
        LOGGER.info("Ingresando a buscar odontologo por ID "+id);
        return ResponseEntity.ok(odontologoService.buscar(id));
    }

    //no va, esta devolviendo una entidad, acopla con la capa de persistencia
    @PatchMapping("/{id}")
    public ResponseEntity<Optional<OdontologoDto>> modificarOdontologo(@PathVariable Integer id, @RequestBody OdontologoDto odontologoDto) throws ResourceNotFoundException {
        LOGGER.info("Ingresando a modificar odontologo");
        return ResponseEntity.ok(odontologoService.actualizar(id, odontologoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
        LOGGER.info("Ingresando a eliminar odontologo");
        odontologoService.eliminar(id);
        return ResponseEntity.ok("Odontologo eliminado");
    }

    /*
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> procesarErrorNotFound(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
     */

    //ESTO CA NO VA PORQU LOS MAPEOS SE HACEN EN EL SERVICE
    /*
    private OdontologoDto mapEntityToDto(Odontologo odontologoEntity) {
        OdontologoDto odontologoDto = new OdontologoDto();
        odontologoDto.id = odontologoEntity.getId();
        odontologoDto.nombre = odontologoEntity.getNombre();
        odontologoDto.apellido = odontologoEntity.getApellido();
        return odontologoDto;
    }
    */
}
