package com.mycompany.ventacine.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entidad que representa un complejo de cine.
 * Un cine tiene empleados, salas, películas, ventas y compras.
 *
 * @author USUARIO
 */
@Entity
@Table(name = "cines")
public class Cine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del cine es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 200, message = "La dirección no puede superar los 200 caracteres")
    @Column(nullable = false)
    private String direccion;

    @ManyToMany
    @JoinTable(
            name = "cine_empleado",
            joinColumns = @JoinColumn(name = "cine_id"),
            inverseJoinColumns = @JoinColumn(name = "empleado_id")
    )
    private List<Empleado> empleados = new ArrayList<>();

    @OneToMany(mappedBy = "cine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sala> salas = new ArrayList<>();

    @OneToMany(mappedBy = "cine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Venta> ventas = new ArrayList<>();

    @OneToMany(mappedBy = "cine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compra> compras = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "cine_pelicula",
            joinColumns = @JoinColumn(name = "cine_id"),
            inverseJoinColumns = @JoinColumn(name = "pelicula_id")
    )
    private List<Pelicula> peliculas = new ArrayList<>();

    public Cine() {
    }

    public Cine(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public void addEmpleado(Empleado empleado) {
        this.empleados.add(empleado);
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    public void addSala(Sala sala) {
        this.salas.add(sala);
        sala.setCine(this);
    }

    /**
     * Devuelve solo las salas comunes (no VIP).
     */
    @Transient
    public List<Sala> getSalasComunes() {
        return salas.stream()
                .filter(s -> !(s instanceof SalaVIP))
                .collect(Collectors.toList());
    }

    /**
     * Devuelve solo las salas VIP.
     */
    @Transient
    public List<SalaVIP> getSalasVIP() {
        return salas.stream()
                .filter(s -> s instanceof SalaVIP)
                .map(s -> (SalaVIP) s)
                .collect(Collectors.toList());
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public void addVenta(Venta venta) {
        this.ventas.add(venta);
        venta.setCine(this);
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public void addCompra(Compra compra) {
        this.compras.add(compra);
        compra.setCine(this);
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public void addPelicula(Pelicula pelicula) {
        this.peliculas.add(pelicula);
    }
}
