package com.mycompany.ventacine.repository;

import com.mycompany.ventacine.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad Pelicula.
 *
 * @author USUARIO
 */
@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
}
