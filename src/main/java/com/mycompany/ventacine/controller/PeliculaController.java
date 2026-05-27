package com.mycompany.ventacine.controller;

import com.mycompany.ventacine.model.Pelicula;
import com.mycompany.ventacine.model.Genero;
import com.mycompany.ventacine.service.FuncionService;
import com.mycompany.ventacine.service.PeliculaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * Controlador CRUD para la gestión de películas.
 *
 * @author USUARIO
 */
@Controller
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private FuncionService funcionService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("peliculas", peliculaService.listarTodas());
        return "peliculas/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("pelicula", new Pelicula());
        model.addAttribute("generos", Genero.values());
        model.addAttribute("titulo", "Nueva Película");
        return "peliculas/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Optional<Pelicula> pelicula = peliculaService.buscarPorId(id);
        if (pelicula.isPresent()) {
            model.addAttribute("pelicula", pelicula.get());
            model.addAttribute("generos", Genero.values());
            model.addAttribute("titulo", "Editar Película");
            return "peliculas/formulario";
        }
        redirect.addFlashAttribute("error", "No se encontró la película solicitada");
        return "redirect:/peliculas";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Pelicula pelicula, BindingResult result,
                          Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("generos", Genero.values());
            model.addAttribute("titulo", pelicula.getId() != null ? "Editar Película" : "Nueva Película");
            return "peliculas/formulario";
        }
        peliculaService.guardar(pelicula);
        redirect.addFlashAttribute("exito", "Película guardada correctamente");
        return "redirect:/peliculas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            // Verificar si la película tiene funciones asociadas
            if (!funcionService.buscarPorPelicula(id).isEmpty()) {
                redirect.addFlashAttribute("error", "No se pudo eliminar la película. Tiene funciones asociadas. Eliminá primero las funciones.");
                return "redirect:/peliculas";
            }
            peliculaService.eliminar(id);
            redirect.addFlashAttribute("exito", "Película eliminada correctamente");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "No se pudo eliminar la película.");
        }
        return "redirect:/peliculas";
    }
}
