/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ventacine;

import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
class Funcion {
    private String Horario;
    private Pelicula pelicula;
    private ArrayList <Entrada> entradas = new ArrayList<>();

    public Funcion() {
    }

    public Funcion(String Horario, Pelicula pelicula) {
        this.Horario = Horario;
        this.pelicula = pelicula;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String Horario) {
        this.Horario = Horario;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public ArrayList<Entrada> getEntradas() {
        return entradas;
    }

    public void setEntradas(ArrayList<Entrada> entradas) {
        this.entradas = entradas;
    }
    public void addEntradas(Entrada entradas) {
        this.entradas.add(entradas);
    }
    
    
}
