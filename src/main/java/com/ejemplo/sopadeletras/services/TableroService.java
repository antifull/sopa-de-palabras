package com.ejemplo.sopadeletras.services;

import com.ejemplo.sopadeletras.dto.TableroDto;
import com.ejemplo.sopadeletras.dto.UbicacionDto;
import com.ejemplo.sopadeletras.persistense.Palabras;
import com.ejemplo.sopadeletras.persistense.Tablero;

import java.util.List;
import java.util.UUID;

public interface TableroService {
    Tablero addTablero(TableroDto tableroDto);
    List<Palabras> listTablero(UUID idTablero);
    Tablero viewTablero(UUID idTablero);
    Boolean solvTablero(UUID idTablero, UbicacionDto ubicacionDto);
}
