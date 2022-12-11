package com.example.Sesion25Paciente.repository;

import com.example.Sesion25Paciente.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer> {

}

