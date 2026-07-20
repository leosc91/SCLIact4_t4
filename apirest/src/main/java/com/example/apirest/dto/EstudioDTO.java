package com.example.apirest.dto;

import jakarta.validation.constraints.NotBlank;

public class EstudioDTO {

    private Long id;

    @NotBlank(message = "El nombre del estudio es obligatorio")
    private String nombre;

    @NotBlank(message = "El país es obligatorio")
    private String pais;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    
}