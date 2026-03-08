package com.polizas.service;

import com.polizas.model.Riesgo;
import com.polizas.model.Poliza;
import com.polizas.model.EstadoPoliza;
import com.polizas.repository.PolizaRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PolizaService {

    private final PolizaRepository repo;

    public PolizaService(PolizaRepository repo){
        this.repo = repo;
    }

    public Poliza renovar(Long id){

        Poliza p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Póliza no encontrada"));

        if(p.getEstado() == EstadoPoliza.CANCELADA){
            throw new RuntimeException("No se puede renovar una póliza cancelada");
        }

        BigDecimal ipc = new BigDecimal("0.10");

        p.setCanonMensual(
                p.getCanonMensual().multiply(BigDecimal.ONE.add(ipc))
        );

        p.setPrima(
                p.getPrima().multiply(BigDecimal.ONE.add(ipc))
        );

        p.setEstado(EstadoPoliza.RENOVADA);

        return repo.save(p);
    }

    public Poliza agregarRiesgo(Long id, Riesgo riesgo){

        Poliza poliza = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Póliza no encontrada"));

        if(poliza.getEstado() == EstadoPoliza.CANCELADA){
            throw new RuntimeException("No se pueden agregar riesgos a una póliza cancelada");
        }

        // Regla actual: póliza INDIVIDUAL solo puede tener 1 riesgo
        if("INDIVIDUAL".equals(poliza.getTipo()) && !poliza.getRiesgos().isEmpty()){
            throw new RuntimeException("Una póliza individual solo puede tener un riesgo");
        }

        // Si quieres la regla del enunciado "solo COLECTIVA puede agregar riesgos",
        // reemplaza lo anterior por:
        // if(!"COLECTIVA".equals(poliza.getTipo())){
        //     throw new RuntimeException("Solo pólizas colectivas pueden agregar riesgos");
        // }

        riesgo.setEstado("ACTIVO");
        riesgo.setPoliza(poliza);

        poliza.getRiesgos().add(riesgo);

        return repo.save(poliza);
    }
}