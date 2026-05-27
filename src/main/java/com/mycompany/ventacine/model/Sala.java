package com.mycompany.ventacine.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa una sala de cine.
 * Utiliza herencia SINGLE_TABLE para soportar SalaVIP.
 *
 * @author USUARIO
 */
@Entity
@Table(name = "salas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_sala", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("COMUN")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El número de sala es obligatorio")
    @Positive(message = "El número de sala debe ser positivo")
    @Column(nullable = false)
    protected int numero;

    @NotNull(message = "La capacidad es obligatoria")
    @Positive(message = "La capacidad debe ser un valor positivo")
    @Column(nullable = false)
    protected int capacidad;

    @ManyToOne
    @JoinColumn(name = "cine_id")
    protected Cine cine;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<Funcion> funciones = new ArrayList<>();

    public Sala() {
    }

    public Sala(int numero, int capacidad) {
        this.numero = numero;
        this.capacidad = capacidad;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    public List<Funcion> getFunciones() {
        return funciones;
    }

    public void setFunciones(List<Funcion> funciones) {
        this.funciones = funciones;
    }

    public void addFuncion(Funcion funcion) {
        this.funciones.add(funcion);
        funcion.setSala(this);
    }

    /**
     * Indica si la sala es VIP.
     */
    @Transient
    public boolean esVip() {
        return this instanceof SalaVIP;
    }
}
