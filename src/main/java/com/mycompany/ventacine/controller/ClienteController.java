package com.mycompany.ventacine.controller;

import com.mycompany.ventacine.model.Cliente;
import com.mycompany.ventacine.model.ClienteVIP;
import com.mycompany.ventacine.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * Controlador CRUD para la gestión de clientes (comunes y VIP).
 *
 * @author USUARIO
 */
@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "clientes/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(@RequestParam(defaultValue = "false") boolean vip, Model model) {
        if (vip) {
            model.addAttribute("cliente", new ClienteVIP());
        } else {
            model.addAttribute("cliente", new Cliente());
        }
        model.addAttribute("esVip", vip);
        model.addAttribute("titulo", vip ? "Nuevo Cliente VIP" : "Nuevo Cliente");
        return "clientes/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());
            model.addAttribute("esVip", cliente.get() instanceof ClienteVIP);
            model.addAttribute("titulo", cliente.get() instanceof ClienteVIP ? "Editar Cliente VIP" : "Editar Cliente");
            return "clientes/formulario";
        }
        redirect.addFlashAttribute("error", "No se encontró el cliente solicitado");
        return "redirect:/clientes";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam(defaultValue = "false") boolean esVip,
                          @RequestParam String nombre,
                          @RequestParam String email,
                          @RequestParam(required = false) Float descuento,
                          @RequestParam(required = false) Long id,
                          RedirectAttributes redirect) {
        try {
            // Validaciones manuales
            if (nombre == null || nombre.trim().isEmpty()) {
                redirect.addFlashAttribute("error", "El nombre del cliente es obligatorio");
                return "redirect:/clientes/nuevo";
            }
            if (email == null || email.trim().isEmpty()) {
                redirect.addFlashAttribute("error", "El email del cliente es obligatorio");
                return "redirect:/clientes/nuevo";
            }

            Cliente cliente;
            if (esVip) {
                ClienteVIP clienteVip = new ClienteVIP();
                clienteVip.setDescuento(descuento != null ? descuento : 0f);
                cliente = clienteVip;
            } else {
                cliente = new Cliente();
            }

            if (id != null) {
                cliente.setId(id);
            }
            cliente.setNombre(nombre.trim());
            cliente.setEmail(email.trim());

            clienteService.guardar(cliente);
            redirect.addFlashAttribute("exito", "Cliente guardado correctamente");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "Error al guardar el cliente: " + e.getMessage());
        }
        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            clienteService.eliminar(id);
            redirect.addFlashAttribute("exito", "Cliente eliminado correctamente");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "No se pudo eliminar el cliente. Puede tener ventas asociadas.");
        }
        return "redirect:/clientes";
    }
}
