package com.mycompany.ventacine.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa una compra de insumos a proveedores.
 *
 * @author USUARIO
 */
@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La fecha de la compra es obligatoria")
    @Column(nullable = false)
    private String fecha;

    @ManyToOne
    @JoinColumn(name = "cine_id")
    private Cine cine;

    @ManyToMany
    @JoinTable(
            name = "compra_insumo",
            joinColumns = @JoinColumn(name = "compra_id"),
            inverseJoinColumns = @JoinColumn(name = "insumo_id")
    )
    private List<Insumo> insumos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "compra_proveedor",
            joinColumns = @JoinColumn(name = "compra_id"),
            inverseJoinColumns = @JoinColumn(name = "proveedor_id")
    )
    private List<Proveedor> proveedores = new ArrayList<>();

    public Compra() {
    }

    public Compra(String fecha) {
        this.fecha = fecha;
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

    public List<Insumo> getInsumos() {
        return insumos;
    }

    public void setInsumos(List<Insumo> insumos) {
        this.insumos = insumos;
    }

    public void addInsumo(Insumo insumo) {
        this.insumos.add(insumo);
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public void addProveedor(Proveedor proveedor) {
        this.proveedores.add(proveedor);
    }
}
