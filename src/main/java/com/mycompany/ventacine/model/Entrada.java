package com.mycompany.ventacine.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Entidad que representa una entrada vendida para una función.
 * Cada entrada tiene un precio y un asiento asignado.
 *
 * @author USUARIO
 */
@Entity
@Table(name = "entradas")
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El precio de la entrada es obligatorio")
    @Positive(message = "El precio debe ser un valor positivo")
    @Column(nullable = false)
    private double precio;

    @NotBlank(message = "El asiento es obligatorio")
    @Column(nullable = false)
    private String asiento;

    @ManyToOne
    @JoinColumn(name = "funcion_id")
    private Funcion funcion;

    public Entrada() {
    }

    public Entrada(double precio, String asiento) {
        this.precio = precio;
        this.asiento = asiento;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }
}
