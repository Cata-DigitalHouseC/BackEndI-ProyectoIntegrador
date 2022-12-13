package com.example.Sesion25Paciente.dto;

import java.util.Date;

public class TurnoDto {

    public Integer id;
    public PacienteDto paciente;
    public OdontologoDto odontologo;
    public Date date;

    public TurnoDto() {
    }

    public TurnoDto(PacienteDto paciente, OdontologoDto odontologo, Date date) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.date = date;
    }
}
