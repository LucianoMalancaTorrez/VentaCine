package com.mycompany.ventacine.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa una venta de entradas.
 * Cada venta tiene una fecha, un pago, clientes y funciones asociadas.
 *
 * @author USUARIO
 */
@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La fecha de la venta es obligatoria")
    @Column(nullable = false)
    private String fecha;

    @ManyToOne
    @JoinColumn(name = "cine_id")
    private Cine cine;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pago_id")
    private Pago pago;

    @ManyToMany
    @JoinTable(
            name = "venta_cliente",
            joinColumns = @JoinColumn(name = "venta_id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_id")
    )
    private List<Cliente> clientes = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "venta_funcion",
            joinColumns = @JoinColumn(name = "venta_id"),
            inverseJoinColumns = @JoinColumn(name = "funcion_id")
    )
    private List<Funcion> funciones = new ArrayList<>();

    public Venta() {
    }

    public Venta(String fecha, Pago pago) {
        this.fecha = fecha;
        this.pago = pago;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void addCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public List<Funcion> getFunciones() {
        return funciones;
    }

    public void setFunciones(List<Funcion> funciones) {
        this.funciones = funciones;
    }

    public void addFuncion(Funcion funcion) {
        this.funciones.add(funcion);
    }
}
