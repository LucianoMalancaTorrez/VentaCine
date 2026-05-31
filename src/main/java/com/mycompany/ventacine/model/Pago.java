package com.mycompany.ventacine.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El monto del pago es obligatorio")
    @Positive(message = "El monto debe ser un valor positivo")
    @Column(nullable = false)
    private double monto;

    @NotNull(message = "El tipo de pago es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPago tipo;

    public Pago() {
    }

    public Pago(double monto, TipoPago tipo) {
        this.monto = monto;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
