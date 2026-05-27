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
public class Venta {
    private String Fecha;
    private Pago pago;
    private ArrayList <Cliente> clientes = new ArrayList <>();
    private ArrayList <Funcion> funciones = new ArrayList <>();

    public Venta() {
    }

    public Venta(String Fecha, Pago pago) {
        this.Fecha = Fecha;
        this.pago = pago;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }
    public void addClientes(Cliente clientes) {
        this.clientes.add(clientes);
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
