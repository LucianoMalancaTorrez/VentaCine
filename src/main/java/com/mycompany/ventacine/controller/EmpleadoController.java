package com.mycompany.ventacine.controller;

import com.mycompany.ventacine.model.Empleado;
import com.mycompany.ventacine.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("empleados", empleadoService.listarTodos());
        return "empleados/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("empleado", new Empleado());
        model.addAttribute("titulo", "Nuevo Empleado");
        return "empleados/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Optional<Empleado> empleado = empleadoService.buscarPorId(id);
        if (empleado.isPresent()) {
            model.addAttribute("empleado", empleado.get());
            model.addAttribute("titulo", "Editar Empleado");
            return "empleados/formulario";
        }
        redirect.addFlashAttribute("error", "No se encontró el empleado solicitado");
        return "redirect:/empleados";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Empleado empleado, BindingResult result,
                          Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", empleado.getId() != null ? "Editar Empleado" : "Nuevo Empleado");
            return "empleados/formulario";
        }
        empleadoService.guardar(empleado);
        redirect.addFlashAttribute("exito", "Empleado guardado correctamente");
        return "redirect:/empleados";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            empleadoService.eliminar(id);
            redirect.addFlashAttribute("exito", "Empleado eliminado correctamente");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "No se pudo eliminar el empleado");
        }
        return "redirect:/empleados";
    }
}
