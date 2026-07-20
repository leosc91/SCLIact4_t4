package com.example.apirest.controller;

import com.example.apirest.dto.VideojuegoDTO;
import com.example.apirest.service.VideojuegoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    @Autowired
    private VideojuegoService videojuegoService;

    // read/get (codigo 200 OK)
    @GetMapping
    public ResponseEntity<List<VideojuegoDTO>> listarVideojuegos() {
        List<VideojuegoDTO> lista = videojuegoService.obtenerTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // create/post (codigo 201 CREATED)
    @PostMapping
    public ResponseEntity<VideojuegoDTO> crearVideojuego(@Valid @RequestBody VideojuegoDTO dto) {
        VideojuegoDTO nuevoVideojuego = videojuegoService.guardar(dto);
        return new ResponseEntity<>(nuevoVideojuego, HttpStatus.CREATED);
    }

    // update/put (codigo 200 OK)
    @PutMapping("/{id}")
    public ResponseEntity<VideojuegoDTO> actualizarVideojuego(
            @PathVariable Long id, 
            @Valid @RequestBody VideojuegoDTO dto) {
        
        VideojuegoDTO videojuegoActualizado = videojuegoService.actualizar(id, dto);
        return new ResponseEntity<>(videojuegoActualizado, HttpStatus.OK);
    }

    // delete (codigo 204 NO CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVideojuego(@PathVariable Long id) {
        videojuegoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}