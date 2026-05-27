package com.mycompany.ventacine.controller;

import com.mycompany.ventacine.model.Insumo;
import com.mycompany.ventacine.service.InsumoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/insumos")
public class InsumoController {

    @Autowired
    private InsumoService insumoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("insumos", insumoService.listarTodos());
        return "insumos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("insumo", new Insumo());
        model.addAttribute("titulo", "Nuevo Insumo");
        return "insumos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Optional<Insumo> insumo = insumoService.buscarPorId(id);
        if (insumo.isPresent()) {
            model.addAttribute("insumo", insumo.get());
            model.addAttribute("titulo", "Editar Insumo");
            return "insumos/formulario";
        }
        redirect.addFlashAttribute("error", "No se encontró el insumo solicitado");
        return "redirect:/insumos";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Insumo insumo, BindingResult result,
                          Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", insumo.getId() != null ? "Editar Insumo" : "Nuevo Insumo");
            return "insumos/formulario";
        }
        insumoService.guardar(insumo);
        redirect.addFlashAttribute("exito", "Insumo guardado correctamente");
        return "redirect:/insumos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            insumoService.eliminar(id);
            redirect.addFlashAttribute("exito", "Insumo eliminado correctamente");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "No se pudo eliminar el insumo");
        }
        return "redirect:/insumos";
    }
}
