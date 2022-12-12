package com.example.Sesion25Paciente.service.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UsuarioService usuarioService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                //.antMatchers("/user/**")
                .antMatchers("/index.html").hasRole("ADMIN") // "/index.html/**"
                //.permitAll()
                .antMatchers("/turnos.html").hasRole("USER")
                //.permitAll()
                .anyRequest()
                .authenticated().and().formLogin()
                .and().logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        DaoAuthenticationProvider autProvider = new DaoAuthenticationProvider();
        autProvider.setPasswordEncoder(bCryptPasswordEncoder);
        autProvider.setUserDetailsService(usuarioService);
        auth.authenticationProvider(autProvider);
    }
}

