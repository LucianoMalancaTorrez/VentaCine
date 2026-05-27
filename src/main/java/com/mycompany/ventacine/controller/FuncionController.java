package com.mycompany.ventacine.controller;

import com.mycompany.ventacine.model.Funcion;
import com.mycompany.ventacine.service.FuncionService;
import com.mycompany.ventacine.service.PeliculaService;
import com.mycompany.ventacine.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * Controlador CRUD para la gestión de funciones.
 *
 * @author USUARIO
 */
@Controller
@RequestMapping("/funciones")
public class FuncionController {

    @Autowired
    private FuncionService funcionService;

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private SalaService salaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("funciones", funcionService.listarTodas());
        return "funciones/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("funcion", new Funcion());
        model.addAttribute("peliculas", peliculaService.listarTodas());
        model.addAttribute("salas", salaService.listarTodas());
        model.addAttribute("titulo", "Nueva Función");
        return "funciones/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Optional<Funcion> funcion = funcionService.buscarPorId(id);
        if (funcion.isPresent()) {
            model.addAttribute("funcion", funcion.get());
            model.addAttribute("peliculas", peliculaService.listarTodas());
            model.addAttribute("salas", salaService.listarTodas());
            model.addAttribute("titulo", "Editar Función");
            return "funciones/formulario";
        }
        redirect.addFlashAttribute("error", "No se encontró la función solicitada");
        return "redirect:/funciones";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Funcion funcion, BindingResult result,
                          Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("peliculas", peliculaService.listarTodas());
            model.addAttribute("salas", salaService.listarTodas());
            model.addAttribute("titulo", funcion.getId() != null ? "Editar Función" : "Nueva Función");
            return "funciones/formulario";
        }
        funcionService.guardar(funcion);
        redirect.addFlashAttribute("exito", "Función guardada correctamente");
        return "redirect:/funciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            funcionService.eliminar(id);
            redirect.addFlashAttribute("exito", "Función eliminada correctamente");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "No se pudo eliminar la función");
        }
        return "redirect:/funciones";
    }
}
