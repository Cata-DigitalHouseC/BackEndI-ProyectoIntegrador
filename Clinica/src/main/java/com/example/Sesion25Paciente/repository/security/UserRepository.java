package com.example.Sesion25Paciente.repository.security;

import com.example.Sesion25Paciente.entities.security.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional() //Solo leer no modificar //readOnly = true
public interface UserRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);
}

