package com.rf.club.repository;

import com.rf.club.model.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngresoRepository extends JpaRepository<Ingreso, Long> {
    Optional<Ingreso> findByDocumento(String documento);
    boolean existsByDocumento(String documento);
}