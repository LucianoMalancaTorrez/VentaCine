package com.mycompany.ventacine.repository;

import com.mycompany.ventacine.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad Cliente (incluye ClienteVIP por herencia).
 *
 * @author USUARIO
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
