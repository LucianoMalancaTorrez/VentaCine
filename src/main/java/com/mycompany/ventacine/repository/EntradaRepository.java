package com.mycompany.ventacine.repository;

import com.mycompany.ventacine.model.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad Entrada.
 *
 * @author USUARIO
 */
@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long> {
}
