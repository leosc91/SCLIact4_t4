package com.example.apirest.service;

import com.example.apirest.dto.VideojuegoDTO;
import com.example.apirest.entity.Estudio;
import com.example.apirest.entity.Videojuego;
import com.example.apirest.repository.EstudioRepository;
import com.example.apirest.repository.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideojuegoService {

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    @Autowired
    private EstudioRepository estudioRepository;

    //convierte Entidad a DTO
    private VideojuegoDTO mapearADto(Videojuego videojuego) {
        VideojuegoDTO dto = new VideojuegoDTO();
        dto.setId(videojuego.getId());
        dto.setTitulo(videojuego.getTitulo());
        dto.setGenero(videojuego.getGenero());
        dto.setPrecio(videojuego.getPrecio());
        dto.setEstudioId(videojuego.getEstudio().getId());
        return dto;
    }

    // read/get (obtiene todos y los convierte a DTO)
    public List<VideojuegoDTO> obtenerTodos() {
        List<Videojuego> videojuegos = videojuegoRepository.findAll();
        return videojuegos.stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    // create/post (recibe un DTO y lo convierte a Entidad y lo guarda)
    public VideojuegoDTO guardar(VideojuegoDTO dto) {
        Estudio estudio = estudioRepository.findById(dto.getEstudioId())
                .orElseThrow(() -> new RuntimeException("Error: Estudio no encontrado"));

        Videojuego videojuego = new Videojuego();
        videojuego.setTitulo(dto.getTitulo());
        videojuego.setGenero(dto.getGenero());
        videojuego.setPrecio(dto.getPrecio());
        videojuego.setEstudio(estudio);

        Videojuego videojuegoGuardado = videojuegoRepository.save(videojuego);

        return mapearADto(videojuegoGuardado);
    }

    // actualizar/put (busca id, actualiza y guarda)
    public VideojuegoDTO actualizar(Long id, VideojuegoDTO dto) {
        Videojuego videojuegoExistente = videojuegoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Videojuego no encontrado"));

        Estudio estudio = estudioRepository.findById(dto.getEstudioId())
                .orElseThrow(() -> new RuntimeException("Error: Estudio no encontrado"));

        videojuegoExistente.setTitulo(dto.getTitulo());
        videojuegoExistente.setGenero(dto.getGenero());
        videojuegoExistente.setPrecio(dto.getPrecio());
        videojuegoExistente.setEstudio(estudio);

        Videojuego actualizado = videojuegoRepository.save(videojuegoExistente);
        return mapearADto(actualizado);
    }

    // eliminar (busca id y borra)
    public void eliminar(Long id) {
        if (!videojuegoRepository.existsById(id)) {
            throw new RuntimeException("Error: Videojuego no encontrado");
        }
        videojuegoRepository.deleteById(id);
    }
}