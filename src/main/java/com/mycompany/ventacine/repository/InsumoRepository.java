package com.mycompany.ventacine.repository;

import com.mycompany.ventacine.model.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad Insumo.
 *
 * @author USUARIO
 */
@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {
}
