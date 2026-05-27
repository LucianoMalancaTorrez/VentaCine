package com.mycompany.ventacine.service;

import com.mycompany.ventacine.model.Cine;
import com.mycompany.ventacine.model.Pelicula;
import com.mycompany.ventacine.repository.CineRepository;
import com.mycompany.ventacine.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de películas.
 *
 * @author USUARIO
 */
@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private CineRepository cineRepository;

    public List<Pelicula> listarTodas() {
        return peliculaRepository.findAll();
    }

    public Optional<Pelicula> buscarPorId(Long id) {
        return peliculaRepository.findById(id);
    }

    public Pelicula guardar(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    @Transactional
    public void eliminar(Long id) {
        // Remover la película de todos los cines que la tengan asociada
        List<Cine> cines = cineRepository.findAll();
        for (Cine cine : cines) {
            cine.getPeliculas().removeIf(p -> p.getId().equals(id));
        }
        cineRepository.saveAll(cines);
        peliculaRepository.deleteById(id);
    }
}
