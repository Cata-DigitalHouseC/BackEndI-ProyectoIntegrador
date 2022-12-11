package com.example.Sesion25Paciente.dto;

public class DomicilioDto {

    //public PacienteDto idPaciente;
    public String Calle;
    public int numero;
    public String localidad;
    public String provincia;

    public DomicilioDto(String Calle, int numero, String localidad, String provincia) {
        this.Calle = Calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }
}
