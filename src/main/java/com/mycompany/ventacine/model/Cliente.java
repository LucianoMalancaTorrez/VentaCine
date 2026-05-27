package com.mycompany.ventacine.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Entidad que representa un cliente del cine.
 * Utiliza herencia SINGLE_TABLE: los clientes VIP se almacenan
 * en la misma tabla diferenciados por la columna 'tipo_cliente'.
 *
 * @author USUARIO
 */
@Entity
@Table(name = "clientes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cliente", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("COMUN")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    @Column(nullable = false)
    protected String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Ingresá un email válido (ejemplo: nombre@correo.com)")
    @Column(nullable = false)
    protected String email;

    public Cliente() {
    }

    public Cliente(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Indica si el cliente es VIP.
     */
    @Transient
    public boolean esVip() {
        return this instanceof ClienteVIP;
    }
}
