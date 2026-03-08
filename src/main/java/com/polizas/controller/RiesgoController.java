package com.polizas.controller;

import com.polizas.model.Riesgo;
import com.polizas.repository.RiesgoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/riesgos")
public class RiesgoController {

    private final RiesgoRepository repo;

    public RiesgoController(RiesgoRepository repo){
        this.repo = repo;
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<Riesgo> cancelar(@PathVariable Long id){

        return repo.findById(id)
                .map(riesgo -> {
                    riesgo.setEstado("CANCELADO");
                    return ResponseEntity.ok(repo.save(riesgo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

}