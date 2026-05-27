package com.mycompany.ventacine.repository;

import com.mycompany.ventacine.model.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio JPA para la entidad Funcion.
 *
 * @author USUARIO
 */
@Repository
public interface FuncionRepository extends JpaRepository<Funcion, Long> {

    /**
     * Busca funciones por el ID de la sala.
     */
    List<Funcion> findBySalaId(Long salaId);

    /**
     * Busca funciones por el ID de la película.
     */
    List<Funcion> findByPeliculaId(Long peliculaId);
}
