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
public class Sala {
    protected int Numero;
    protected int Capacidad;
    protected ArrayList <Funcion> funciones = new ArrayList<>();

    public Sala() {
    }

    public Sala(int Numero, int Capacidad) {
        this.Numero = Numero;
        this.Capacidad = Capacidad;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public int getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(int Capacidad) {
        this.Capacidad = Capacidad;
    }

    public ArrayList<Funcion> getFunciones() {
        return funciones;
    }

    public void setFunciones(ArrayList<Funcion> funciones) {
        this.funciones = funciones;
    }

    public void addFunciones(Funcion funciones) {
        this.funciones.add(funciones);
    }
    
}
