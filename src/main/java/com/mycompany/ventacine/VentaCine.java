/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ventacine;

/**
 *
 * @author USUARIO
 */

//2.	Ejercicio 1 - Implementar desde el diagrama de clases – REQUERIMIENTOS QUE DEBE MOSTRAR UN MENSAJE POR PANTALLA DE SALIDA:

//-	Mencione el nombre, dni de 2 empleados que trabajen en el cine. Para este mismo Cine, debe mencionas cuantas salas Vip (al menos 2) y cuantas salas comunes (al menos 2) se disponen, que funciones se dan en cada una de ellas (horarios), con las entradas (precio y asiento) y las películas (título y genero) que se muestran en cada sala. 

//-	Para este mismo Cine, debe mencionas cuantas ventas (al menos 2) se realizaron, para al menos una función ya instanciada del sistema. Para dicha función (horario), menciones las entradas (precio y asiento) y las películas (título y genero) que se muestran para venta. Además, no olvide mencionar los datos de los clientes al que se le vendió la entrada. 

//-	En todos los casos podrá crear constructores y los métodos que le sean necesarios para reflejar adecuadamente las asociaciones que se observa en el diagrama. 

 

    public class VentaCine {
    public static void main(String[] args) {

        Cine cine = new Cine("Cine Mendoza", "Av. Siempre Viva 123");

        
        Empleado e1 = new Empleado("Juan Perez", 12345678);
        Empleado e2 = new Empleado("Maria Lopez", 87654321);

        cine.addEmpleados(e1);
        cine.addEmpleados(e2);

         
        Pelicula p1 = new Pelicula("Avengers", Genero.ACCION);
        Pelicula p2 = new Pelicula("Titanic", Genero.DRAMA);

         
        Funcion f1 = new Funcion("20:00", p1);
        f1.addEntradas(new Entrada(2000, "A1"));
        f1.addEntradas(new Entrada(2000, "A2"));

        Funcion f2 = new Funcion("22:00", p2);
        f2.addEntradas(new Entrada(1800, "B1"));
        f2.addEntradas(new Entrada(1800, "B2"));

         
        Sala s1 = new Sala(1, 100);
        s1.addFunciones(f1);

        Sala s2 = new Sala(2, 120);
        s2.addFunciones(f2);

        cine.addSalas(s1);
        cine.addSalas(s2);

         
        SalaVIP sv1 = new SalaVIP("Asientos reclinables", 3, 50);
        sv1.addFunciones(f1);

        SalaVIP sv2 = new SalaVIP("Servicio de comida", 4, 60);
        sv2.addFunciones(f2);

        cine.addSalasVIP(sv1);
        cine.addSalasVIP(sv2);

         
        Cliente c1 = new Cliente("Carlos", "carlos@mail.com");
        Cliente c2 = new Cliente("Ana", "ana@mail.com");

         
        Venta v1 = new Venta("25/03/2026", new Pago(4000, TipoPago.EFECTIVO));
        v1.addClientes(c1);
        v1.addFunciones(f1);

        Venta v2 = new Venta("25/03/2026", new Pago(3600, TipoPago.TARJETA));
        v2.addClientes(c2);
        v2.addFunciones(f2);

        cine.addVentas(v1);
        cine.addVentas(v2);

         

        System.out.println("=== EMPLEADOS ===");
        System.out.println("Nombre: " + cine.getEmpleados().get(0).getNombre() + " DNI: " + cine.getEmpleados().get(0).getDni());
        System.out.println("Nombre: " + cine.getEmpleados().get(1).getNombre() + " DNI: " + cine.getEmpleados().get(1).getDni());

        System.out.println("\n=== SALAS COMUNES ===");
        System.out.println("Cantidad: " + cine.getSalas().size());

         
        System.out.println("Sala N°: " + cine.getSalas().get(0).getNumero());
        System.out.println("Horario: " + cine.getSalas().get(0).getFunciones().get(0).getHorario());
        System.out.println("Pelicula: " + cine.getSalas().get(0).getFunciones().get(0).getPelicula().getTitulo()
                + " (" + cine.getSalas().get(0).getFunciones().get(0).getPelicula().getGenero() + ")");
        System.out.println("Entrada: Precio " +
                cine.getSalas().get(0).getFunciones().get(0).getEntradas().get(0).getPrecio()
                + " Asiento " +
                cine.getSalas().get(0).getFunciones().get(0).getEntradas().get(0).getAsiento());

         
        System.out.println("\nSala N°: " + cine.getSalas().get(1).getNumero());
        System.out.println("Horario: " + cine.getSalas().get(1).getFunciones().get(0).getHorario());
        System.out.println("Pelicula: " + cine.getSalas().get(1).getFunciones().get(0).getPelicula().getTitulo()
                + " (" + cine.getSalas().get(1).getFunciones().get(0).getPelicula().getGenero() + ")");
        System.out.println("Entrada: Precio " +
                cine.getSalas().get(1).getFunciones().get(0).getEntradas().get(0).getPrecio()
                + " Asiento " +
                cine.getSalas().get(1).getFunciones().get(0).getEntradas().get(0).getAsiento());

        System.out.println("\n=== SALAS VIP ===");
        System.out.println("Cantidad: " + cine.getSalasVIP().size());

 
        System.out.println("Sala VIP N°: " + cine.getSalasVIP().get(0).getNumero());
        System.out.println("Beneficio: " + cine.getSalasVIP().get(0).getBeneficios());

        System.out.println("Horario: " + cine.getSalasVIP().get(0).getFunciones().get(0).getHorario());
        System.out.println("Pelicula: " + 
        cine.getSalasVIP().get(0).getFunciones().get(0).getPelicula().getTitulo() +
        " (" + cine.getSalasVIP().get(0).getFunciones().get(0).getPelicula().getGenero() + ")");

        System.out.println("Entrada: Precio " +
        cine.getSalasVIP().get(0).getFunciones().get(0).getEntradas().get(0).getPrecio() +
        " Asiento " +
        cine.getSalasVIP().get(0).getFunciones().get(0).getEntradas().get(0).getAsiento());

 
        System.out.println("\nSala VIP N°: " + cine.getSalasVIP().get(1).getNumero());
        System.out.println("Beneficio: " + cine.getSalasVIP().get(1).getBeneficios());

        System.out.println("Horario: " + cine.getSalasVIP().get(1).getFunciones().get(0).getHorario());
        System.out.println("Pelicula: " + 
        cine.getSalasVIP().get(1).getFunciones().get(0).getPelicula().getTitulo() +
        " (" + cine.getSalasVIP().get(1).getFunciones().get(0).getPelicula().getGenero() + ")");

        System.out.println("Entrada: Precio " +
        cine.getSalasVIP().get(1).getFunciones().get(0).getEntradas().get(0).getPrecio() +
        " Asiento " +
        cine.getSalasVIP().get(1).getFunciones().get(0).getEntradas().get(0).getAsiento());

        System.out.println("\n=== VENTAS ===");
        System.out.println("Cantidad: " + cine.getVentas().size());

 
        System.out.println("\nFecha: " + cine.getVentas().get(0).getFecha());

 
        System.out.println("Cliente: " + 
        cine.getVentas().get(0).getClientes().get(0).getNombre() +
        " - " +
        cine.getVentas().get(0).getClientes().get(0).getEmail()
        );

 
        System.out.println("Horario: " + 
        cine.getVentas().get(0).getFunciones().get(0).getHorario()
        );

 
        System.out.println("Pelicula: " +
        cine.getVentas().get(0).getFunciones().get(0).getPelicula().getTitulo() +
        " (" +
        cine.getVentas().get(0).getFunciones().get(0).getPelicula().getGenero() +
        ")"
        );

 
        System.out.println("Entrada: Precio " +
        cine.getVentas().get(0).getFunciones().get(0).getEntradas().get(0).getPrecio() +
        " Asiento " +
        cine.getVentas().get(0).getFunciones().get(0).getEntradas().get(0).getAsiento()
        );


 
        System.out.println("\nFecha: " + cine.getVentas().get(1).getFecha());

 
        System.out.println("Cliente: " + 
        cine.getVentas().get(1).getClientes().get(0).getNombre() +
        " - " +
        cine.getVentas().get(1).getClientes().get(0).getEmail()
        );

 
        System.out.println("Horario: " + 
        cine.getVentas().get(1).getFunciones().get(0).getHorario()
        );

 
        System.out.println("Pelicula: " +
        cine.getVentas().get(1).getFunciones().get(0).getPelicula().getTitulo() +
        " (" +
        cine.getVentas().get(1).getFunciones().get(0).getPelicula().getGenero() +
        ")"
        );

 
        System.out.println("Entrada: Precio " +
        cine.getVentas().get(1).getFunciones().get(0).getEntradas().get(0).getPrecio() +
        " Asiento " +
        cine.getVentas().get(1).getFunciones().get(0).getEntradas().get(0).getAsiento()
        );
    }
}

 