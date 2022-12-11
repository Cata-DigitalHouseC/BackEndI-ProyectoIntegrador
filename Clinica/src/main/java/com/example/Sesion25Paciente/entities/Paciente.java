package com.example.Sesion25Paciente.entities;


import javax.persistence.*;

import java.util.Date;

import com.example.Sesion25Paciente.dto.DomicilioDto;
import com.example.Sesion25Paciente.entities.Domicilio;

@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;

    //@OneToOne
    //@JoinColumn(name="idPaciente")
    private String domicilio;
    private String dni;
    private Date fechaAlta;


    /*
    public Paciente(Integer id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    */

    public Paciente() {
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public String getApellido() {

        return apellido;
    }

    public void setApellido(String apellido) {

        this.apellido = apellido;
    }

    public String getDomicilio() {

        return domicilio;
    }

    public void setDomicilio(String domicilio) {

        this.domicilio = domicilio;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

}
