package com.example.Sesion25Paciente;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaApplication {

	public static void main(String[] args) {

		PropertyConfigurator.configure("log4j.properties"); //Configuramos el log4j
		SpringApplication.run(ClinicaApplication.class, args);
	}

}
