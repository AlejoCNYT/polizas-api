package com.polizas.controller;

import com.polizas.model.Riesgo;
import com.polizas.model.Poliza;
import com.polizas.repository.PolizaRepository;
import com.polizas.service.PolizaService;

import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polizas")
public class PolizaController {

    private final PolizaRepository repo;
    private final PolizaService service;

    public PolizaController(PolizaRepository repo, PolizaService service){
        this.repo = repo;
        this.service = service;
    }

    @Operation(summary = "Listar todas las pólizas")
    @GetMapping
    public List<Poliza> listar(){
        return repo.findAll();
    }

    @Operation(summary = "Crear una nueva póliza")
    @PostMapping
    public Poliza crear(@Valid @RequestBody Poliza poliza){

        poliza.setEstado("ACTIVA");

        return repo.save(poliza);
    }

    @Operation(summary = "Obtener póliza por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Poliza> obtener(@PathVariable Long id){

        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Renovar póliza aplicando incremento IPC")
    @PostMapping("/{id}/renovar")
    public ResponseEntity<Poliza> renovar(@PathVariable Long id){

        try{
            return ResponseEntity.ok(service.renovar(id));
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().build();
        }

    }

    @Operation(summary = "Cancelar póliza y todos sus riesgos")
    @PostMapping("/{id}/cancelar")
    public ResponseEntity<Poliza> cancelar(@PathVariable Long id){

        Poliza p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Póliza no encontrada"));

        p.setEstado("CANCELADA");

        if(p.getRiesgos() != null){
            p.getRiesgos().forEach(r -> r.setEstado("CANCELADO"));
        }

        return ResponseEntity.ok(repo.save(p));
    }

    @Operation(summary = "Agregar riesgo a una póliza")
    @PostMapping("/{id}/riesgos")
    public ResponseEntity<Poliza> agregarRiesgo(
            @PathVariable Long id,
            @Valid @RequestBody Riesgo riesgo
    ){

        try{
            return ResponseEntity.ok(service.agregarRiesgo(id, riesgo));
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().build();
        }

    }

    @Operation(summary = "Listar riesgos de una póliza")
    @GetMapping("/{id}/riesgos")
    public ResponseEntity<List<Riesgo>> obtenerRiesgos(@PathVariable Long id){

        return repo.findById(id)
                .map(poliza -> ResponseEntity.ok(poliza.getRiesgos()))
                .orElse(ResponseEntity.notFound().build());
    }

}