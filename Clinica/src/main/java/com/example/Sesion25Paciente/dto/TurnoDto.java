package com.example.Sesion25Paciente.dto;

import com.example.Sesion25Paciente.entities.Odontologo;
import com.example.Sesion25Paciente.entities.Paciente;

import java.util.Date;

public class TurnoDto {

    public Integer id;
    public Integer pacienteId;
    public Integer odontologoId;
    public Date fecha;

    public TurnoDto() {
    }

    public TurnoDto(Integer pacienteId, Integer odontologoId, Date fecha) {
        this.pacienteId = pacienteId;
        this.odontologoId = odontologoId;
        this.fecha = fecha;
    }
}
