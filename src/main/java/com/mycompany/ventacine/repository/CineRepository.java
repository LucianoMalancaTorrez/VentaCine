package com.mycompany.ventacine.repository;

import com.mycompany.ventacine.model.Cine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CineRepository extends JpaRepository<Cine, Long> {
}
