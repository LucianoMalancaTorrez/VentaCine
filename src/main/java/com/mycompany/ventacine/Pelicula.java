/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ventacine;

/**
 *
 * @author USUARIO
 */
class Pelicula implements IPromocion {
    private String Titulo;
    private Genero genero;

    public Pelicula() {
    }

    public Pelicula(String Titulo, Genero genero) {
        this.Titulo = Titulo;
        this.genero = genero;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
    
     
     

    @Override
    public float ObtenerDescuento() {
        return 8.70f; 
    };
}
