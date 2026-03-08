package com.polizas.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Riesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String direccion;

    private String arrendatario;

    private String estado;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "poliza_id")
    private Poliza poliza;

    public Long getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getArrendatario() {
        return arrendatario;
    }

    public String getEstado() {
        return estado;
    }

    public Poliza getPoliza() {
        return poliza;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setArrendatario(String arrendatario) {
        this.arrendatario = arrendatario;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }
}