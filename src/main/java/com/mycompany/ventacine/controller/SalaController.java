package com.mycompany.ventacine.controller;

import com.mycompany.ventacine.model.Sala;
import com.mycompany.ventacine.model.SalaVIP;
import com.mycompany.ventacine.service.CineService;
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
 * Controlador CRUD para la gestión de salas (comunes y VIP).
 *
 * @author USUARIO
 */
@Controller
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @Autowired
    private CineService cineService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("salas", salaService.listarTodas());
        return "salas/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNueva(@RequestParam(defaultValue = "false") boolean vip, Model model) {
        if (vip) {
            model.addAttribute("sala", new SalaVIP());
        } else {
            model.addAttribute("sala", new Sala());
        }
        model.addAttribute("esVip", vip);
        model.addAttribute("cines", cineService.listarTodos());
        model.addAttribute("titulo", vip ? "Nueva Sala VIP" : "Nueva Sala");
        return "salas/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Optional<Sala> sala = salaService.buscarPorId(id);
        if (sala.isPresent()) {
            model.addAttribute("sala", sala.get());
            model.addAttribute("esVip", sala.get() instanceof SalaVIP);
            model.addAttribute("cines", cineService.listarTodos());
            model.addAttribute("titulo", sala.get() instanceof SalaVIP ? "Editar Sala VIP" : "Editar Sala");
            return "salas/formulario";
        }
        redirect.addFlashAttribute("error", "No se encontró la sala solicitada");
        return "redirect:/salas";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam(defaultValue = "false") boolean esVip,
                          @RequestParam int numero,
                          @RequestParam int capacidad,
                          @RequestParam(required = false) Long cineId,
                          @RequestParam(required = false) String beneficios,
                          @RequestParam(required = false) Long id,
                          RedirectAttributes redirect) {
        try {
            Sala sala;
            if (esVip) {
                SalaVIP salaVip = new SalaVIP();
                salaVip.setBeneficios(beneficios);
                sala = salaVip;
            } else {
                sala = new Sala();
            }

            if (id != null) {
                sala.setId(id);
            }
            sala.setNumero(numero);
            sala.setCapacidad(capacidad);

            if (cineId != null) {
                cineService.buscarPorId(cineId).ifPresent(sala::setCine);
            }

            salaService.guardar(sala);
            redirect.addFlashAttribute("exito", "Sala guardada correctamente");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "Error al guardar la sala: " + e.getMessage());
        }
        return "redirect:/salas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            Optional<Sala> sala = salaService.buscarPorId(id);
            if (sala.isPresent() && !sala.get().getFunciones().isEmpty()) {
                redirect.addFlashAttribute("error", "No se pudo eliminar la sala. Primero eliminá las funciones asociadas.");
                return "redirect:/salas";
            }
            salaService.eliminar(id);
            redirect.addFlashAttribute("exito", "Sala eliminada correctamente");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "No se pudo eliminar la sala.");
        }
        return "redirect:/salas";
    }
}
