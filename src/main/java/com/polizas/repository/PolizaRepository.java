package com.polizas.repository;

import com.polizas.model.Poliza;
import com.polizas.model.EstadoPoliza;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolizaRepository extends JpaRepository<Poliza, Long> {

    List<Poliza> findByTipo(String tipo);

    List<Poliza> findByEstado(EstadoPoliza estado);

    List<Poliza> findByTipoAndEstado(String tipo, EstadoPoliza estado);

}