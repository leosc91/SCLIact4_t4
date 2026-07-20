package com.example.apirest.controller;

import com.example.apirest.dto.EstudioDTO;
import com.example.apirest.service.EstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/estudios")
public class EstudioController {

    @Autowired
    private EstudioService estudioService;

    // read/get (codigo 200 OK)
    @GetMapping
    public ResponseEntity<List<EstudioDTO>> listarEstudios() {
        List<EstudioDTO> lista = estudioService.obtenerTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}