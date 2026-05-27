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
public class SalaVIP extends Sala{
    private String Beneficios;

    public SalaVIP() {
    }

    public SalaVIP(String Beneficios, int Numero, int Capacidad) {
        super(Numero, Capacidad);
        this.Beneficios = Beneficios;
    }

    public String getBeneficios() {
        return Beneficios;
    }

    public void setBeneficios(String Beneficios) {
        this.Beneficios = Beneficios;
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
    
    
}
