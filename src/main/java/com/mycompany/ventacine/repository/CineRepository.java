package com.mycompany.ventacine.repository;

import com.mycompany.ventacine.model.Cine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad Cine.
 * Proporciona operaciones CRUD automáticas.
 *
 * @author USUARIO
 */
@Repository
public interface CineRepository extends JpaRepository<Cine, Long> {
}
