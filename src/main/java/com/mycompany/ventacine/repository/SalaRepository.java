package com.mycompany.ventacine.repository;

import com.mycompany.ventacine.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad Sala (incluye SalaVIP por herencia).
 *
 * @author USUARIO
 */
@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
}
