package com.mycompany.ventacine.config;

import com.mycompany.ventacine.model.*;
import com.mycompany.ventacine.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Carga datos de ejemplo al iniciar la aplicación por primera vez.
 * Solo inserta datos si la base está vacía para no duplicar registros.
 *
 * @author USUARIO
 */
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CineRepository cineRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private FuncionRepository funcionRepository;
    @Autowired
    private EntradaRepository entradaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private InsumoRepository insumoRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private CompraRepository compraRepository;

    @Override
    public void run(String... args) throws Exception {
        // Solo cargar datos si la base está vacía
        if (cineRepository.count() > 0) {
            return;
        }

        System.out.println("Cargando datos de ejemplo...");

        // Crear el cine
        Cine cine = new Cine("Cine Center Mendoza", "Padre Jorge Contreras 1300, M5502 JMA, Mendoza");
        cineRepository.save(cine);

        // Empleados
        Empleado e1 = new Empleado("Juan Pérez", 12345678);
        Empleado e2 = new Empleado("María López", 87654321);
        empleadoRepository.save(e1);
        empleadoRepository.save(e2);
        cine.addEmpleado(e1);
        cine.addEmpleado(e2);

        // Películas (con pósters)
        Pelicula p1 = new Pelicula("Venom: El Último Baile", Genero.ACCION, "https://image.tmdb.org/t/p/w500/k42Owka8v91Eu9emoga2LQp5FIk.jpg");
        Pelicula p2 = new Pelicula("Gladiador II", Genero.ACCION, "https://image.tmdb.org/t/p/w500/2cxhvwyEwRlysAmRH4iodkvo0z5.jpg");
        Pelicula p3 = new Pelicula("El Contador de Cartas", Genero.DRAMA, "https://image.tmdb.org/t/p/w500/gSMBuuVeLbiDaQrPDkXFyVctyOF.jpg");
        Pelicula p4 = new Pelicula("¿Qué Pasó Ayer?", Genero.COMEDIA, "https://image.tmdb.org/t/p/w500/uluhlXubGu1VxJMOFOk3TDlLSbN.jpg");
        Pelicula p5 = new Pelicula("Alien: Romulus", Genero.SUSPENSO, "https://image.tmdb.org/t/p/w500/b33nnKl1GSFbao4l3fJQJQmdfys.jpg");
        peliculaRepository.save(p1);
        peliculaRepository.save(p2);
        peliculaRepository.save(p3);
        peliculaRepository.save(p4);
        peliculaRepository.save(p5);

        cine.addPelicula(p1);
        cine.addPelicula(p2);
        cine.addPelicula(p3);
        cine.addPelicula(p4);
        cine.addPelicula(p5);

        // Salas comunes
        Sala s1 = new Sala(1, 120);
        s1.setCine(cine);
        Sala s2 = new Sala(2, 100);
        s2.setCine(cine);
        salaRepository.save(s1);
        salaRepository.save(s2);

        // Salas VIP
        SalaVIP sv1 = new SalaVIP(3, 50, "Asientos reclinables, servicio de comida");
        sv1.setCine(cine);
        SalaVIP sv2 = new SalaVIP(4, 60, "Pantalla 4K, sonido Dolby Atmos");
        sv2.setCine(cine);
        salaRepository.save(sv1);
        salaRepository.save(sv2);

        // Funciones
        Funcion f1 = new Funcion("14:30", p1);
        f1.setSala(s1);
        Funcion f2 = new Funcion("17:00", p2);
        f2.setSala(s1);
        Funcion f3 = new Funcion("20:00", p1);
        f3.setSala(s2);
        Funcion f4 = new Funcion("22:30", p3);
        f4.setSala(s2);
        Funcion f5 = new Funcion("19:00", p4);
        f5.setSala(sv1);
        Funcion f6 = new Funcion("21:30", p5);
        f6.setSala(sv2);
        funcionRepository.save(f1);
        funcionRepository.save(f2);
        funcionRepository.save(f3);
        funcionRepository.save(f4);
        funcionRepository.save(f5);
        funcionRepository.save(f6);

        // Entradas
        Entrada en1 = new Entrada(2500, "A1");
        en1.setFuncion(f1);
        Entrada en2 = new Entrada(2500, "A2");
        en2.setFuncion(f1);
        Entrada en3 = new Entrada(2800, "B1");
        en3.setFuncion(f2);
        Entrada en4 = new Entrada(3500, "C1");
        en4.setFuncion(f5);
        entradaRepository.save(en1);
        entradaRepository.save(en2);
        entradaRepository.save(en3);
        entradaRepository.save(en4);

        // Clientes
        Cliente c1 = new Cliente("Carlos García", "carlos@gmail.com");
        Cliente c2 = new Cliente("Ana Rodríguez", "ana.rodriguez@hotmail.com");
        ClienteVIP c3 = new ClienteVIP("Roberto Sánchez", "roberto.vip@gmail.com", 15.0f);
        clienteRepository.save(c1);
        clienteRepository.save(c2);
        clienteRepository.save(c3);

        // Ventas
        Venta v1 = new Venta("22/05/2026", new Pago(5000, TipoPago.EFECTIVO));
        v1.setCine(cine);
        v1.addCliente(c1);
        v1.addFuncion(f1);
        ventaRepository.save(v1);

        Venta v2 = new Venta("22/05/2026", new Pago(3500, TipoPago.TARJETA));
        v2.setCine(cine);
        v2.addCliente(c3);
        v2.addFuncion(f5);
        ventaRepository.save(v2);

        // Insumos
        Insumo i1 = new Insumo("Pochoclos grandes", 850);
        Insumo i2 = new Insumo("Gaseosa 500ml", 650);
        Insumo i3 = new Insumo("Combo nachos", 1200);
        insumoRepository.save(i1);
        insumoRepository.save(i2);
        insumoRepository.save(i3);

        // Proveedores
        Proveedor prov1 = new Proveedor("Distribuidora del Sur", "(261) 555-1234", "Calle Godoy Cruz 789, Mendoza");
        Proveedor prov2 = new Proveedor("Snacks Argentinos SA", "(11) 4555-6789", "Av. Corrientes 1200, CABA");
        proveedorRepository.save(prov1);
        proveedorRepository.save(prov2);

        // Compras
        Compra comp1 = new Compra("20/05/2026");
        comp1.setCine(cine);
        comp1.addInsumo(i1);
        comp1.addInsumo(i2);
        comp1.addProveedor(prov1);
        compraRepository.save(comp1);

        cineRepository.save(cine);

        System.out.println("Datos de ejemplo cargados correctamente.");
    }
}
