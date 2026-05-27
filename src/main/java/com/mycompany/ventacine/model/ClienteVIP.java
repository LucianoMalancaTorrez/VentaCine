package com.mycompany.ventacine.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Entidad que representa un cliente VIP del cine.
 * Hereda de Cliente y agrega un descuento especial.
 *
 * @author USUARIO
 */
@Entity
@DiscriminatorValue("VIP")
public class ClienteVIP extends Cliente {

    private float descuento;

    public ClienteVIP() {
    }

    public ClienteVIP(String nombre, String email, float descuento) {
        super(nombre, email);
        this.descuento = descuento;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }
}
