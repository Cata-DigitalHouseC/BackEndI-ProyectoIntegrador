package com.example.Sesion25Paciente.controller;

import com.example.Sesion25Paciente.dto.OdontologoDto;
import com.example.Sesion25Paciente.exception.ResourceNotFoundException;
import com.example.Sesion25Paciente.service.OdontologoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import util.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static javax.management.Query.value;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(OdontologoController.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionOdontologoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OdontologoService odontologoService;

    @Test
    public void verOdontologo() throws Exception {
        //Arrange -> Preparar el escenario, crear objetos
        when(odontologoService.buscar(1)).
                thenReturn(Optional.of(new OdontologoDto("Juan", "Perez", 1234)));
        //Act
        mockMvc.perform(get("/odontologos/1") //simula el get
                .contentType(MediaType.APPLICATION_JSON)) //tipo de contenido json
                .andExpect(status().isOk()) //espera el ok
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) //espera el tipo de contenido json
                .andExpect(jsonPath("$.nombre").value("Juan")) //espera que el nombre sea Juan
                .andExpect(jsonPath("$.apellido").value("Perez")) //espera que el apellido sea Perez
                .andExpect(jsonPath("$.matricula").value(1234)); //espera que la matricula sea 1234
        //Assert
        verify(odontologoService).buscar(1); //verifica que se haya llamado al metodo buscar del odontologoService
    }

    @Test
    public void testListarOdontologos() throws Exception {
        //Arrange
        Set<OdontologoDto> odontologos =null;
        odontologos.add(new OdontologoDto("Juan", "Perez", 1234));
        odontologos.add(new OdontologoDto("Pedro", "Gomez", 5678));

        //Act
        when(odontologoService.listar()).thenReturn(odontologos); //simula el proceso de listar
        mockMvc.perform(get("/odontologos").contentType(MediaType.APPLICATION_JSON)) //simula el get
                .andExpect(status().isOk()) //espera el ok
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) //espera el tipo de contenido json
                .andExpect(jsonPath("$[0].nombre").value("Juan")) //espera que el nombre sea Juan
                .andExpect(jsonPath("$[0].apellido").value("Perez")) //espera que el apellido sea Perez
                .andExpect(jsonPath("$[0].matricula").value(1234)) //espera que la matricula sea 1234
                .andExpect(jsonPath("$[1].nombre").value("Pedro")) //espera que el nombre sea Pedro
                .andExpect(jsonPath("$[1].apellido").value("Gomez")) //espera que el apellido sea Gomez
                .andExpect(jsonPath("$[1].matricula").value(5678)) //espera que la matricula sea 5678
                .andExpect(jsonPath("$", hasSize(2))); //espera que la lista tenga 2 elementos
        //.andExpect(content().json("[{'nombre':'Juan','apellido':'Perez','matricula':1234},{'nombre':'Pedro','apellido':'Gomez','matricula':5678}]")); //espera que el contenido sea el json
        //Assert
        verify(odontologoService).listar(); //verifica que se haya llamado al metodo listar del odontologoService
    }

    @Test
    public void guardarOdontologo() throws Exception {
        //Arrange
        OdontologoDto odontologo = new OdontologoDto("Juan", "Perez", 1234);
        when(odontologoService.guardar(odontologo)).then(invocation ->{
            OdontologoDto odontologoDto = invocation.getArgument(0);
            return odontologoDto;
        });
        //Act
        mockMvc.perform(post("/odontologos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Perez"))
                .andExpect(jsonPath("$.matricula").value(1234));
        //Assert
        verify(odontologoService).guardar(odontologo);
    }

    @Test
    public void registrarOdontologo() throws Exception {
        //Arrange -> Preparar el escenario, crear objetos
        OdontologoDto dto = new OdontologoDto("Juan","Perez",1234);
        //Act
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders
                        .post("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(dto)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk())
                        .andReturn();
        //Assert
        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void listarOdontologos() throws Exception {
        //Arrange -> Preparar el escenario, crear objetos
        OdontologoDto dto = new OdontologoDto("Juan","Perez",1234);
        //Act
        MvcResult response = mockMvc.perform(get("/odontologos")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(dto)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk())
                        .andReturn();
        //Assert
        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void buscarOdontologo() throws Exception {
        //Arrange -> Preparar el escenario, crear objetos
        OdontologoDto dto = new OdontologoDto("Juan","Perez",1234);
        //Act
        MvcResult response = mockMvc.perform(get("/odontologos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(dto)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk())
                        .andReturn();
        //Assert
        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void modificarOdontologo() throws Exception {
        //Arrange -> Preparar el escenario, crear objetos
        OdontologoDto dto = new OdontologoDto("Juan","Perez",1234);
        //Act
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders
                        .put("/odontologos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(dto)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk())
                        .andReturn();
        //Assert
        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void eliminarOdontologo() throws Exception {
        //Arrange -> Preparar el escenario, crear objetos
        OdontologoDto dto = new OdontologoDto("Juan","Perez",1234);
        //Act
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/odontologos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(dto)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk())
                        .andReturn();
        //Assert
        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

}
