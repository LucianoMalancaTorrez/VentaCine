package com.mycompany.ventacine.service;

import com.mycompany.ventacine.model.Funcion;
import com.mycompany.ventacine.model.Venta;
import com.mycompany.ventacine.repository.FuncionRepository;
import com.mycompany.ventacine.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de funciones cinematográficas.
 *
 * @author USUARIO
 */
@Service
public class FuncionService {

    @Autowired
    private FuncionRepository funcionRepository;

    @Autowired
    private VentaRepository ventaRepository;

    public List<Funcion> listarTodas() {
        return funcionRepository.findAll();
    }

    public Optional<Funcion> buscarPorId(Long id) {
        return funcionRepository.findById(id);
    }

    public List<Funcion> buscarPorSala(Long salaId) {
        return funcionRepository.findBySalaId(salaId);
    }

    public List<Funcion> buscarPorPelicula(Long peliculaId) {
        return funcionRepository.findByPeliculaId(peliculaId);
    }

    public Funcion guardar(Funcion funcion) {
        return funcionRepository.save(funcion);
    }

    @Transactional
    public void eliminar(Long id) {
        // Remover la función de todas las ventas que la tengan asociada
        List<Venta> ventas = ventaRepository.findAll();
        for (Venta venta : ventas) {
            venta.getFunciones().removeIf(f -> f.getId().equals(id));
        }
        ventaRepository.saveAll(ventas);
        // La función tiene cascade ALL en entradas, así que se borran automáticamente
        funcionRepository.deleteById(id);
    }
}
