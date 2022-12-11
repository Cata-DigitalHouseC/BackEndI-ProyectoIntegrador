package com.example.Sesion25Paciente.entities;



import javax.persistence.Id;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Turno
{

    @Id
    @SequenceGenerator(name = "turno_sequence", sequenceName = "turno_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turno_sequence")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name="odontologo_id")
    private Odontologo odontologo;
    private Date date;

    public Turno(Integer id) {
        this.id = id;
    }

    public Turno(Integer id, Paciente paciente, Odontologo odontologo, Date date) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
