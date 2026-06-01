package com.mycompany.ventacine.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;

@Entity
@DiscriminatorValue("VIP")
public class SalaVIP extends Sala {

    @Size(max = 300, message = "Los beneficios no pueden superar los 300 caracteres")
    private String beneficios;

    public SalaVIP() {
    }

    public SalaVIP(int numero, int capacidad, String beneficios) {
        super(numero, capacidad);
        this.beneficios = beneficios;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }
}
