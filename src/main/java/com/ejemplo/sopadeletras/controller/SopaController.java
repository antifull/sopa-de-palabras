package com.ejemplo.sopadeletras.controller;

import com.ejemplo.sopadeletras.dto.TableroDto;
import com.ejemplo.sopadeletras.dto.UbicacionDto;
import com.ejemplo.sopadeletras.persistense.Palabras;
import com.ejemplo.sopadeletras.persistense.Tablero;
import com.ejemplo.sopadeletras.services.TableroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/alphabetSoup/", produces = "application/json")
public class SopaController {

    @Autowired
    private TableroService tableroService;
    @Operation(summary = "crearSopaPalabras")
    @PostMapping(path = "/add", consumes = "application/json")
    public ResponseEntity<Tablero> crearSopaPalabras(@RequestBody @Valid TableroDto tableroDto){
        return new ResponseEntity<>(tableroService.addTablero(tableroDto), HttpStatus.CREATED);
//        return ResponseEntity.created(tableroService.addTablero(tableroDto));
    }
    @Operation(summary = "listadoPalabrasSopaPalabras")
    @GetMapping(path = "/list/{idTablero}")
    public ResponseEntity<List<Palabras>> listadoPalabrasSopaPalabras(@PathVariable UUID idTablero){
        return new ResponseEntity<>(tableroService.listTablero(idTablero), HttpStatus.OK);
//        return ResponseEntity.ok(tableroService.listTablero(idTablero));

    }

    @Operation(summary = "verSopaPalabras")
    @GetMapping(path = "/view/{idTablero}")
    public ResponseEntity<String> verSopaPalabras(@PathVariable UUID idTablero){
        return new ResponseEntity<>(tableroService.viewTablero(idTablero).getTablero(), HttpStatus.OK);
    }

    /**
     * @param ubicacionDto Identificador del tablero
     * @return ResponseEntity<> listado de palabras
     */
    @Operation(summary = "encontrarPalabrasSopaPalabras")
    @PutMapping(path = "/solv/{idTablero}", consumes = "application/json")
    public ResponseEntity<?> encontrarPalabrasSopaPalabras(@PathVariable UUID idTablero, @RequestBody @Valid UbicacionDto ubicacionDto){
        return tableroService.solvTablero(idTablero, ubicacionDto) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}