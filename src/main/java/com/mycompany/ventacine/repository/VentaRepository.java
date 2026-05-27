package com.mycompany.ventacine.repository;

import com.mycompany.ventacine.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad Venta.
 *
 * @author USUARIO
 */
@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}
