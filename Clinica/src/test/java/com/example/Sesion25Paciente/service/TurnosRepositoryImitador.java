/*
package com.example.Sesion25Paciente.service;

import com.example.Sesion25Paciente.entities.Odontologo;
import com.example.Sesion25Paciente.entities.Paciente;
import com.example.Sesion25Paciente.entities.Turno;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TurnosRepositoryImitador implements IDao<Turno> {

    @Override
    public Turno guardar(Turno turno) {
        return turno;
    }

    @Override
    public Turno buscar(int id) {
        return new Turno(id, new Paciente(1,"",""),
                new Odontologo(1,"","",1),
                new Date()) ;
    }

    @Override
    public Turno actualizar(Turno turno) {
        return null;
    }

    @Override
    public void borrar(int id) {

    }

    @Override
    public void eliminar(Integer id) {
    }

    @Override
    public List<Turno> buscarTodos() {
        return new ArrayList<>(
                Arrays.asList(new Turno(1, new Paciente(1,"",""),
                        new Odontologo(1,"","",1),
                        new Date()),
                        new Turno(2, new Paciente(2,"",""),
                                new Odontologo(2,"","",2),
                                new Date())
                )
        );
    }


}

 */

