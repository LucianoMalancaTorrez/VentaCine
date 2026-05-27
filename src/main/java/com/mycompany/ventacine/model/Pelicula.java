package com.mycompany.ventacine.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidad que representa una película del cine.
 * Implementa la interfaz IPromocion para aplicar descuentos.
 *
 * @author USUARIO
 */
@Entity
@Table(name = "peliculas")
public class Pelicula implements IPromocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título de la película es obligatorio")
    @Size(max = 150, message = "El título no puede superar los 150 caracteres")
    @Column(nullable = false)
    private String titulo;

    @NotNull(message = "El género es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;

    @Size(max = 500, message = "La URL de la imagen no puede superar los 500 caracteres")
    @Column(length = 500)
    private String imagenUrl;

    public Pelicula() {
    }

    public Pelicula(String titulo, Genero genero) {
        this.titulo = titulo;
        this.genero = genero;
    }

    public Pelicula(String titulo, Genero genero, String imagenUrl) {
        this.titulo = titulo;
        this.genero = genero;
        this.imagenUrl = imagenUrl;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    /**
     * Descuento promocional aplicable a la película.
     * Devuelve un porcentaje fijo del 8.70%.
     */
    @Override
    @Transient
    public float obtenerDescuento() {
        return 8.70f;
    }
}

