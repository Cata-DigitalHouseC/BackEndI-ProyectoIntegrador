package com.example.Sesion25Paciente.dto;

import java.util.Date;

public class PacienteDto {

    public Integer id;
    public String nombre;
    public String apellido;
    public String domicilio;
    public String dni;
    public Date fechaAlta;

    public PacienteDto(String nombre, String apellido, String domicilio, String dni, Date fechaAlta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaAlta = fechaAlta;
    }
}
