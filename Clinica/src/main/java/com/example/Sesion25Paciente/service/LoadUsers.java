package com.example.Sesion25Paciente.service;

import com.example.Sesion25Paciente.entities.security.Rol;
import com.example.Sesion25Paciente.entities.security.Usuario;
import com.example.Sesion25Paciente.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoadUsers implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public LoadUsers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String password1 = passwordEncoder.encode("1234");
        userRepository.save(new Usuario(1, "Pablo", "pablo@email.com","pmv", password1, Rol.USER));

        String password2 = passwordEncoder.encode("1234");
        userRepository.save(new Usuario(2, "Am=na", "ana@correo.com","ana", password2, Rol.ADMIN));
    }
}
