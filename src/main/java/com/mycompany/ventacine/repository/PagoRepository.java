package com.mycompany.ventacine.repository;

import com.mycompany.ventacine.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad Pago.
 *
 * @author USUARIO
 */
@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
}
