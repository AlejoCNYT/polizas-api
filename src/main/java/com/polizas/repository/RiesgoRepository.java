package com.polizas.repository;

import com.polizas.model.Riesgo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiesgoRepository extends JpaRepository<Riesgo, Long> {
}