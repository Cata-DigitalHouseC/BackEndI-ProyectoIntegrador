package com.example.Sesion25Paciente.entities;

import javax.persistence.*;

@Entity
public class Domicilio {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "domicilio_sequence")
    @SequenceGenerator(name = "domicilio_sequence", sequenceName = "domicilio_sequence", allocationSize = 1)
    //@OneToOne
    //private Paciente idPaciente;

    private String Calle;
    private int numero;
    private String localidad;
    private String provincia;

    public Domicilio(String Calle, int numero, String localidad, String provincia) {
        this.Calle = Calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }


    public Domicilio() {

    }
}
