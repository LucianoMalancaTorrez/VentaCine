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
class Empleado {
    private String Nombre;
    private int Dni;
    private ArrayList <Cine> cines = new ArrayList <>();

    public Empleado() {
    }

    public Empleado(String Nombre, int Dni) {
        this.Nombre = Nombre;
        this.Dni = Dni;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getDni() {
        return Dni;
    }

    public void setDni(int Dni) {
        this.Dni = Dni;
    }

    public ArrayList<Cine> getCines() {
        return cines;
    }

    public void setCines(ArrayList<Cine> cines) {
        this.cines = cines;
    }
    public void addCines(Cine cines) {
        this.cines.add(cines);
    }
    
           
}
