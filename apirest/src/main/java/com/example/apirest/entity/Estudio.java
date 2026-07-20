package com.example.apirest.entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "estudios")
public class Estudio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String pais;

    @OneToMany(mappedBy = "estudio", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Videojuego> videojuegos;

    public Estudio() {

    }

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

    public List<Videojuego> getVideojuegos() {
     return videojuegos;
    }

    public void setVideojuegos(List<Videojuego> videojuegos) {
     this.videojuegos = videojuegos;
    }
}