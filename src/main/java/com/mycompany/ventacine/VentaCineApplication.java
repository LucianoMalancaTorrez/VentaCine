package com.mycompany.ventacine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación VentaCine.
 * Inicia el servidor web embebido y configura Spring Boot.
 *
 * @author USUARIO
 */
@SpringBootApplication
public class VentaCineApplication {

    public static void main(String[] args) {
        SpringApplication.run(VentaCineApplication.class, args);
    }
}
