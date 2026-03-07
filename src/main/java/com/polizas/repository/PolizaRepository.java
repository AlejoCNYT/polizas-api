package com.polizas.repository;

import com.polizas.model.Poliza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolizaRepository extends JpaRepository<Poliza, Long> {
}