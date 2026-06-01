package com.mycompany.ventacine.controller;

import com.mycompany.ventacine.model.Proveedor;
import com.mycompany.ventacine.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proveedores", proveedorService.listarTodos());
        return "proveedores/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        model.addAttribute("titulo", "Nuevo Proveedor");
        return "proveedores/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Optional<Proveedor> proveedor = proveedorService.buscarPorId(id);
        if (proveedor.isPresent()) {
            model.addAttribute("proveedor", proveedor.get());
            model.addAttribute("titulo", "Editar Proveedor");
            return "proveedores/formulario";
        }
        redirect.addFlashAttribute("error", "No se encontró el proveedor solicitado");
        return "redirect:/proveedores";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Proveedor proveedor, BindingResult result,
                          Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", proveedor.getId() != null ? "Editar Proveedor" : "Nuevo Proveedor");
            return "proveedores/formulario";
        }
        proveedorService.guardar(proveedor);
        redirect.addFlashAttribute("exito", "Proveedor guardado correctamente");
        return "redirect:/proveedores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            proveedorService.eliminar(id);
            redirect.addFlashAttribute("exito", "Proveedor eliminado correctamente");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "No se pudo eliminar el proveedor");
        }
        return "redirect:/proveedores";
    }
}
