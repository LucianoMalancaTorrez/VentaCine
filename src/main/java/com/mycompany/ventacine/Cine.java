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
public class Cine {
    private String Nombre;
    private String Direccion;
    private ArrayList<Pelicula> peliculas = new ArrayList<>();
    private ArrayList<Venta> ventas = new ArrayList<>();
    private ArrayList<SalaVIP> SalasVIP = new ArrayList<>();
    private ArrayList<Sala> Salas = new ArrayList<>();
    private ArrayList<Compra> Compras = new ArrayList<>();
    private ArrayList<Empleado> Empleados = new ArrayList<>();

    public Cine() {
    }

    public Cine(String Nombre, String Direccion) {
        this.Nombre = Nombre;
        this.Direccion = Direccion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
    public void addPeliculas(Pelicula peliculas) {
        this.peliculas.add(peliculas);
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }
    public void addVentas(Venta ventas) {
        this.ventas.add(ventas);
    }

    public ArrayList<SalaVIP> getSalasVIP() {
        return SalasVIP;
    }

    public void setSalasVIP(ArrayList<SalaVIP> SalasVIP) {
        this.SalasVIP = SalasVIP;
    }
    public void addSalasVIP(SalaVIP SalasVIP) {
        this.SalasVIP.add(SalasVIP);
    }

    public ArrayList<Sala> getSalas() {
        return Salas;
    }

    public void setSalas(ArrayList<Sala> Salas) {
        this.Salas = Salas;
    }
    public void addSalas(Sala Salas) {
        this.Salas.add(Salas);
    }

    public ArrayList<Compra> getCompras() {
        return Compras;
    }

    public void setCompras(ArrayList<Compra> Compras) {
        this.Compras = Compras;
    }
    public void addCompras(Compra Compras) {
        this.Compras.add(Compras);
    }

    public ArrayList<Empleado> getEmpleados() {
        return Empleados;
    }

    public void setEmpleados(ArrayList<Empleado> Empleados) {
        this.Empleados = Empleados;
    }
    public void addEmpleados(Empleado Empleados) {
        this.Empleados.add(Empleados);
    }
    
    
}
