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
public class Compra {
    private String Fecha;
    private ArrayList <Insumo> insumos = new ArrayList <>();
    private ArrayList <Proveedor> proveedores = new ArrayList <>();

    public Compra() {
    }

    public Compra(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public ArrayList<Insumo> getInsumos() {
        return insumos;
    }

    public void setInsumos(ArrayList<Insumo> insumos) {
        this.insumos = insumos;
    }
    public void addInsumos(Insumo insumos) {
        this.insumos.add(insumos);
    }

    public ArrayList<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(ArrayList<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }
    public void addProveedores(Proveedor proveedores) {
        this.proveedores.add(proveedores);
    }

    
    
    
}
