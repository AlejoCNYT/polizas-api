package com.polizas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Poliza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El tipo de póliza es obligatorio")
    private String tipo;

    private String estado;

    @NotNull
    @DecimalMin(value = "0.01", message = "El canon mensual debe ser mayor a 0")
    private BigDecimal canonMensual;

    @NotNull
    @DecimalMin(value = "0.01", message = "La prima debe ser mayor a 0")
    private BigDecimal prima;

    @OneToMany(mappedBy = "poliza", cascade = CascadeType.ALL)
    private List<Riesgo> riesgos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getCanonMensual() {
        return canonMensual;
    }

    public void setCanonMensual(BigDecimal canonMensual) {
        this.canonMensual = canonMensual;
    }

    public BigDecimal getPrima() {
        return prima;
    }

    public void setPrima(BigDecimal prima) {
        this.prima = prima;
    }

    public List<Riesgo> getRiesgos() {
        return riesgos;
    }

    public void setRiesgos(List<Riesgo> riesgos) {
        this.riesgos = riesgos;
    }
}