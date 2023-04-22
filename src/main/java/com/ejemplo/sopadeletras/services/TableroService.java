package com.ejemplo.sopadeletras.services;

import com.ejemplo.sopadeletras.dto.TableroDto;
import com.ejemplo.sopadeletras.dto.UbicacionDto;
import com.ejemplo.sopadeletras.persistense.Palabras;
import com.ejemplo.sopadeletras.persistense.Tablero;
import com.ejemplo.sopadeletras.persistense.TableroPalabras;

import java.util.List;

public interface TableroService {
    Tablero addTablero(TableroDto tableroDto);
    List<Palabras> listTablero(Long idTablero);
    String viewTablero(Long idTablero);
    Boolean solvTablero(UbicacionDto ubicacionDto);
}
