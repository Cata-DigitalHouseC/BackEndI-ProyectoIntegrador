package com.example.Sesion25Paciente.service; //package com.example.Sesion25Paciente

import com.example.Sesion25Paciente.dto.DomicilioDto;
import com.example.Sesion25Paciente.dto.PacienteDto;
import com.example.Sesion25Paciente.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacienteServiceTest {

    private PacienteService pacienteService;

    @Test
    public void testCrearPaciente() throws ResourceNotFoundException {
        //Arrange
        //PacienteDto pacienteDton1 = new PacienteDto("Juan","Hernandez",new DomicilioDto("Calle",15,"Loc1","Prov1"),"1017",new Date());
        PacienteDto pacienteDton1 = new PacienteDto("Juan","Hernandez","Calle 1","1017",new Date());
        pacienteService.guardar(pacienteDton1);
        //Act
        Optional<PacienteDto> pacienteDto1 = pacienteService.buscar(1);
        //Assert
        assertTrue(pacienteDto1.isPresent());
    }

    @Test
    public void testListaPacientes() throws ResourceNotFoundException {
        //Arrange
        PacienteDto pacienteDton1 = new PacienteDto("Juan","Hernandez","Calle 1","1017",new Date());
        pacienteService.guardar(pacienteDton1);
        PacienteDto pacienteDton2 = new PacienteDto("Carlos","Montero","Calle 2","1018",new Date());
        pacienteService.guardar(pacienteDton2);
        PacienteDto pacienteDton3 = new PacienteDto("Benny","Arbelaez","Calle 3","1019",new Date());
        pacienteService.guardar(pacienteDton3);
        //Act
        Set<PacienteDto> pacientes = pacienteService.listar();
        //Assert
        assertEquals(3, pacientes.size());
    }

    @Test
    public void testActualizarPaciente() throws ResourceNotFoundException {
        //Arrange
        PacienteDto pacienteDtonActual = new PacienteDto("Juan","Hernandez","Calle 1","1017",new Date());
        pacienteService.guardar(pacienteDtonActual); //guardar paciente 1 Original
        PacienteDto pacienteDtonNuevo = new PacienteDto("Juan","Perez","Calle 2","1018",new Date());
        pacienteService.actualizar(1,pacienteDtonNuevo);
       //Act
        Optional<PacienteDto> pacienteDtoActualizado = pacienteService.buscar(1);
        //Assert
        assertEquals(pacienteDtoActualizado.get().apellido,pacienteDtonNuevo.apellido);
    }

    @Test
    public void testEliminarPaciente() throws ResourceNotFoundException {
        //Arrange
        PacienteDto pacienteDton1 = new PacienteDto("Juan","Hernandez","Calle 1","1017",new Date());
        pacienteService.guardar(pacienteDton1);
        pacienteService.eliminar(1);
        //Act
        Optional<PacienteDto> pacienteDto1 = pacienteService.buscar(1);
        //Assert
        assertFalse(pacienteDto1.isPresent());
    }
}