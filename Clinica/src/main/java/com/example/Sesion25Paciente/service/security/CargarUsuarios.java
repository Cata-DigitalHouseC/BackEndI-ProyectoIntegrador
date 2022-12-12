package com.example.Sesion25Paciente.service.security;

import com.example.Sesion25Paciente.entities.security.Rol;
import com.example.Sesion25Paciente.entities.security.Usuario;
import com.example.Sesion25Paciente.repository.security.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargarUsuarios implements ApplicationRunner {

    private UsuarioRepository userRepository;

    @Autowired
    public CargarUsuarios(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String password1 = passwordEncoder.encode("12347");
        userRepository.save(new Usuario( "Pablo", "pablo@email.com","pmv", password1, Rol.USER));

        String password2 = passwordEncoder.encode("12342");
        userRepository.save(new Usuario("Ana", "ana@correo.com","ana", password2, Rol.ADMIN));

        String password3 = passwordEncoder.encode("12345");
        userRepository.save(new Usuario("CataAdmin", "cataxtoAdmin@correo.com","cataAdmin", password3, Rol.ADMIN));

        String password4 = passwordEncoder.encode("12343");
        userRepository.save(new Usuario("CataUser", "cataxtoUser@correo.com","cataUser", password4, Rol.USER));
    }
}
