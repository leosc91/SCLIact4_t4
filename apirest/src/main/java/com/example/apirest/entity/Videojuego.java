package com.example.apirest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "videojuegos")
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String genero;
    private double precio;

    @ManyToOne
    @JoinColumn(name = "estudio_id")
    private Estudio estudio;



    public Videojuego() {

     }

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) {
         this.id = id; 
    }

    public String getTitulo() {
         return titulo; 
    }

    public void setTitulo(String titulo) {
         this.titulo = titulo; 
    }

    public String getGenero() {
         return genero; 
    }

    public void setGenero(String genero) {
         this.genero = genero; 
    }

    public double getPrecio() {
         return precio; 
    }

    public void setPrecio(double precio) {
         this.precio = precio; 
    }

    public Estudio getEstudio() {
         return estudio; 
    }

    public void setEstudio(Estudio estudio) {
         this.estudio = estudio; 
    }

}