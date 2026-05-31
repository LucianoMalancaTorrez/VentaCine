package com.mycompany.ventacine;

class Entrada {
    private double precio;
    private String asiento;

    public Entrada() {
    }

    public Entrada(double precio, String asiento) {
        this.precio = precio;
        this.asiento = asiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }
    
    
}
