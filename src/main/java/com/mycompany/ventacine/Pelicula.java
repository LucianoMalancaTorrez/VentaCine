package com.mycompany.ventacine;

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
