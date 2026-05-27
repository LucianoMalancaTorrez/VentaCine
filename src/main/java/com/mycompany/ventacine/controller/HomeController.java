package com.mycompany.ventacine.controller;

import com.mycompany.ventacine.model.Funcion;
import com.mycompany.ventacine.model.Pelicula;
import com.mycompany.ventacine.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Controlador de la página principal (cartelera del cine).
 * Muestra las películas en cartel con sus funciones y horarios.
 *
 * @author USUARIO
 */
@Controller
public class HomeController {

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private FuncionService funcionService;

    @Autowired
    private SalaService salaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VentaService ventaService;

    @Autowired
    private EmpleadoService empleadoService;

    /**
     * Página principal: muestra la cartelera de películas con sus horarios.
     */
    @GetMapping("/")
    public String index(Model model) {
        List<Pelicula> peliculas = peliculaService.listarTodas();
        List<Funcion> funciones = funcionService.listarTodas();

        // Agrupar funciones por película para mostrar los horarios de cada una
        Map<Long, List<Funcion>> funcionesPorPelicula = funciones.stream()
                .filter(f -> f.getPelicula() != null)
                .collect(Collectors.groupingBy(f -> f.getPelicula().getId()));

        model.addAttribute("peliculas", peliculas);
        model.addAttribute("funcionesPorPelicula", funcionesPorPelicula);

        // Estadísticas para el dashboard
        model.addAttribute("totalPeliculas", peliculas.size());
        model.addAttribute("totalSalas", salaService.listarTodas().size());
        model.addAttribute("totalClientes", clienteService.listarTodos().size());
        model.addAttribute("totalVentas", ventaService.listarTodas().size());

        return "index";
    }
}
