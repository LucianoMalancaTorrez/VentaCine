package com.mycompany.ventacine.controller;

import com.mycompany.ventacine.model.*;
import com.mycompany.ventacine.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private FuncionService funcionService;
    @Autowired
    private CineService cineService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ventas", ventaService.listarTodas());
        return "ventas/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("venta", new Venta());
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("funciones", funcionService.listarTodas());
        model.addAttribute("cines", cineService.listarTodos());
        model.addAttribute("tiposPago", TipoPago.values());
        model.addAttribute("titulo", "Nueva Venta");
        return "ventas/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Optional<Venta> venta = ventaService.buscarPorId(id);
        if (venta.isPresent()) {
            model.addAttribute("venta", venta.get());
            model.addAttribute("clientes", clienteService.listarTodos());
            model.addAttribute("funciones", funcionService.listarTodas());
            model.addAttribute("cines", cineService.listarTodos());
            model.addAttribute("tiposPago", TipoPago.values());
            model.addAttribute("titulo", "Editar Venta");
            return "ventas/formulario";
        }
        redirect.addFlashAttribute("error", "No se encontró la venta solicitada");
        return "redirect:/ventas";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam String fecha,
                          @RequestParam double montoTotal,
                          @RequestParam String tipoPago,
                          @RequestParam(required = false) List<Long> clienteIds,
                          @RequestParam(required = false) List<Long> funcionIds,
                          @RequestParam(required = false) Long cineId,
                          @RequestParam(required = false) Long id,
                          RedirectAttributes redirect) {
        try {
            if (fecha == null || fecha.trim().isEmpty()) {
                redirect.addFlashAttribute("error", "La fecha es obligatoria");
                return "redirect:/ventas/nueva";
            }

            Venta venta = new Venta();
            if (id != null) {
                venta.setId(id);
            }
            venta.setFecha(fecha.trim());

            // Crear el pago asociado
            Pago pago = new Pago();
            pago.setMonto(montoTotal);
            pago.setTipo(TipoPago.valueOf(tipoPago));
            venta.setPago(pago);

            // Asociar el cine
            if (cineId != null) {
                cineService.buscarPorId(cineId).ifPresent(venta::setCine);
            }

            // Asociar clientes seleccionados
            if (clienteIds != null && !clienteIds.isEmpty()) {
                List<Cliente> clientes = new ArrayList<>();
                for (Long clienteId : clienteIds) {
                    clienteService.buscarPorId(clienteId).ifPresent(clientes::add);
                }
                venta.setClientes(clientes);
            }

            // Asociar funciones seleccionadas
            if (funcionIds != null && !funcionIds.isEmpty()) {
                List<Funcion> funciones = new ArrayList<>();
                for (Long funcionId : funcionIds) {
                    funcionService.buscarPorId(funcionId).ifPresent(funciones::add);
                }
                venta.setFunciones(funciones);
            }

            ventaService.guardar(venta);
            redirect.addFlashAttribute("exito", "Venta registrada correctamente");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "Error al registrar la venta: " + e.getMessage());
        }
        return "redirect:/ventas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            ventaService.eliminar(id);
            redirect.addFlashAttribute("exito", "Venta eliminada correctamente");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "No se pudo eliminar la venta");
        }
        return "redirect:/ventas";
    }
}
