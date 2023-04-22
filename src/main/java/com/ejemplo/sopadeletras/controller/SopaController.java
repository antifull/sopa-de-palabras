package com.ejemplo.sopadeletras.controller;

import com.ejemplo.sopadeletras.dto.TableroDto;
import com.ejemplo.sopadeletras.persistense.Palabras;
import com.ejemplo.sopadeletras.persistense.Tablero;
import com.ejemplo.sopadeletras.persistense.TableroPalabras;
import com.ejemplo.sopadeletras.services.TableroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/alphabetSoup/", produces = "application/json")
public class SopaController {

    @Autowired
    private TableroService tableroService;

    @PostMapping(path = "/add", consumes = "application/json")
    public ResponseEntity<Tablero> crearSopaPalabras(@RequestBody TableroDto tableroDto){
        Tablero tablero = tableroService.addTablero(tableroDto);
        return new ResponseEntity<>(tablero, HttpStatus.CREATED);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<Palabras>> listadoPalabrasSopaPalabras(Long idTablero){
        List<Palabras> listaTableroPalabras = tableroService.listTablero(idTablero);
        return new ResponseEntity<>(listaTableroPalabras, HttpStatus.OK);
    }
}