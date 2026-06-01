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

    
    @GetMapping("/")
    public String index(Model model) {
        List<Pelicula> peliculas = peliculaService.listarTodas();
        List<Funcion> funciones = funcionService.listarTodas();

        Map<Long, List<Funcion>> funcionesPorPelicula = funciones.stream()
                .filter(f -> f.getPelicula() != null)
                .collect(Collectors.groupingBy(f -> f.getPelicula().getId()));

        model.addAttribute("peliculas", peliculas);
        model.addAttribute("funcionesPorPelicula", funcionesPorPelicula);

        model.addAttribute("totalPeliculas", peliculas.size());
        model.addAttribute("totalSalas", salaService.listarTodas().size());
        model.addAttribute("totalClientes", clienteService.listarTodos().size());
        model.addAttribute("totalVentas", ventaService.listarTodas().size());

        return "index";
    }
}
