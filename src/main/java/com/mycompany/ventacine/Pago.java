package com.mycompany.ventacine;

public class Pago {
    private double monto;
    private TipoPago tipo;

    public Pago() {
    }

    public Pago(double monto, TipoPago tipo) {
        this.monto = monto;
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public TipoPago getTipo() {
        return tipo;
    }

    public void setTipo(TipoPago tipo) {
        this.tipo = tipo;
    }
    
    
}
