package com.mycompany.ventacine.controller;

import com.mycompany.ventacine.model.Compra;
import com.mycompany.ventacine.model.Insumo;
import com.mycompany.ventacine.model.Proveedor;
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
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;
    @Autowired
    private InsumoService insumoService;
    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private CineService cineService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("compras", compraService.listarTodas());
        return "compras/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("compra", new Compra());
        model.addAttribute("insumos", insumoService.listarTodos());
        model.addAttribute("proveedores", proveedorService.listarTodos());
        model.addAttribute("cines", cineService.listarTodos());
        model.addAttribute("titulo", "Nueva Compra");
        return "compras/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Optional<Compra> compra = compraService.buscarPorId(id);
        if (compra.isPresent()) {
            model.addAttribute("compra", compra.get());
            model.addAttribute("insumos", insumoService.listarTodos());
            model.addAttribute("proveedores", proveedorService.listarTodos());
            model.addAttribute("cines", cineService.listarTodos());
            model.addAttribute("titulo", "Editar Compra");
            return "compras/formulario";
        }
        redirect.addFlashAttribute("error", "No se encontró la compra solicitada");
        return "redirect:/compras";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam String fecha,
                          @RequestParam(required = false) List<Long> insumoIds,
                          @RequestParam(required = false) List<Long> proveedorIds,
                          @RequestParam(required = false) Long cineId,
                          @RequestParam(required = false) Long id,
                          RedirectAttributes redirect) {
        try {
            Compra compra = new Compra();
            if (id != null) {
                compra.setId(id);
            }
            compra.setFecha(fecha.trim());

            if (cineId != null) {
                cineService.buscarPorId(cineId).ifPresent(compra::setCine);
            }

            if (insumoIds != null) {
                List<Insumo> insumos = new ArrayList<>();
                for (Long insumoId : insumoIds) {
                    insumoService.buscarPorId(insumoId).ifPresent(insumos::add);
                }
                compra.setInsumos(insumos);
            }

            if (proveedorIds != null) {
                List<Proveedor> proveedores = new ArrayList<>();
                for (Long proveedorId : proveedorIds) {
                    proveedorService.buscarPorId(proveedorId).ifPresent(proveedores::add);
                }
                compra.setProveedores(proveedores);
            }

            compraService.guardar(compra);
            redirect.addFlashAttribute("exito", "Compra registrada correctamente");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "Error al registrar la compra: " + e.getMessage());
        }
        return "redirect:/compras";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            compraService.eliminar(id);
            redirect.addFlashAttribute("exito", "Compra eliminada correctamente");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "No se pudo eliminar la compra");
        }
        return "redirect:/compras";
    }
}
