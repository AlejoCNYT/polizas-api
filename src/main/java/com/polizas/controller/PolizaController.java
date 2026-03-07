package com.polizas.controller;

import com.polizas.model.Riesgo;
import com.polizas.model.Poliza;
import com.polizas.repository.PolizaRepository;
import com.polizas.service.PolizaService;

import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;

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
    public Poliza obtener(@PathVariable Long id){
        return repo.findById(id).orElseThrow();
    }

    @Operation(summary = "Renovar póliza aplicando incremento IPC")
    @PostMapping("/{id}/renovar")
    public Poliza renovar(@PathVariable Long id){
        return service.renovar(id);
    }

    @Operation(summary = "Cancelar póliza")
    @PostMapping("/{id}/cancelar")
    public Poliza cancelar(@PathVariable Long id){

        Poliza p = repo.findById(id).orElseThrow();

        p.setEstado("CANCELADA");

        return repo.save(p);
    }

    @Operation(summary = "Agregar riesgo a una póliza")
    @PostMapping("/{id}/riesgos")
    public Poliza agregarRiesgo(
            @PathVariable Long id,
            @Valid @RequestBody Riesgo riesgo
    ){
        return service.agregarRiesgo(id, riesgo);
    }

}