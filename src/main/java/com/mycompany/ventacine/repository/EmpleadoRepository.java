package com.mycompany.ventacine.repository;

import com.mycompany.ventacine.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad Empleado.
 *
 * @author USUARIO
 */
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
