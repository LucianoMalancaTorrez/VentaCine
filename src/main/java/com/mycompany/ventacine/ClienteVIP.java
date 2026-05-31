package com.mycompany.ventacine;

public class ClienteVIP extends Cliente {
    private float Descuento;

    public ClienteVIP() {
    }

    public ClienteVIP(float Descuento, String nombre, String email) {
        super(nombre, email);
        this.Descuento = Descuento;
    }

    public float getDescuento() {
        return Descuento;
    }

    public void setDescuento(float Descuento) {
        this.Descuento = Descuento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
