package com.example.apirest.service;

import com.example.apirest.dto.EstudioDTO;
import com.example.apirest.entity.Estudio;
import com.example.apirest.repository.EstudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudioService {

    @Autowired
    private EstudioRepository estudioRepository;

    // Entidad a DTO
    private EstudioDTO mapearADto(Estudio estudio) {
        EstudioDTO dto = new EstudioDTO();
        dto.setId(estudio.getId());
        dto.setNombre(estudio.getNombre());
        dto.setPais(estudio.getPais());
        return dto;
    }

    // lectura/get
    public List<EstudioDTO> obtenerTodos() {
        List<Estudio> estudios = estudioRepository.findAll();
        return estudios.stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }
}