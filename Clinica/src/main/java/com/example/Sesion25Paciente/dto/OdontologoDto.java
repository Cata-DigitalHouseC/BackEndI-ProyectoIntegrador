package com.example.Sesion25Paciente.dto;

import javax.validation.constraints.NotNull;

public class OdontologoDto {
    //data sensible, puede estar este pkg dentro de service
    public Integer id;
    @NotNull
    public String nombre;

    @NotNull
    public String apellido;
    public Integer matricula;

    public OdontologoDto() {
    }

    public OdontologoDto(String nombre, String apellido, Integer matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }
}
