package com.polizas.service;

import com.polizas.model.Riesgo;
import com.polizas.model.Poliza;
import com.polizas.repository.PolizaRepository;
import org.springframework.stereotype.Service;

@Service
public class PolizaService {

    private final PolizaRepository repo;

    public PolizaService(PolizaRepository repo){
        this.repo = repo;
    }

    public Poliza renovar(Long id){

        Poliza p = repo.findById(id).orElseThrow();

        if("CANCELADA".equals(p.getEstado())){
            throw new RuntimeException("No se puede renovar una póliza cancelada");
        }

        double ipc = 0.10;

        p.setCanonMensual(p.getCanonMensual()*(1+ipc));
        p.setPrima(p.getPrima()*(1+ipc));
        p.setEstado("RENOVADA");

        return repo.save(p);
    }

    public Poliza agregarRiesgo(Long id, Riesgo riesgo){

        Poliza poliza = repo.findById(id).orElseThrow();

        riesgo.setPoliza(poliza);

        poliza.getRiesgos().add(riesgo);

        return repo.save(poliza);

    }

}