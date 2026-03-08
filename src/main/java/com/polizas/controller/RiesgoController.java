package com.polizas.controller;

import com.polizas.model.Riesgo;
import com.polizas.repository.RiesgoRepository;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/riesgos")
public class RiesgoController {

    private final RiesgoRepository repo;

    public RiesgoController(RiesgoRepository repo){
        this.repo = repo;
    }

    @PostMapping("/{id}/cancelar")
    public Riesgo cancelar(@PathVariable Long id){

        Riesgo riesgo = repo.findById(id).orElseThrow();

        riesgo.setEstado("CANCELADO");

        return repo.save(riesgo);
    }
}