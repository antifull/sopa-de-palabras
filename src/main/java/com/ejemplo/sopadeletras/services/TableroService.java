package com.ejemplo.sopadeletras.services;

import com.ejemplo.sopadeletras.dto.TableroDto;
import com.ejemplo.sopadeletras.persistense.Tablero;

public interface TableroService {
    Tablero addTablero(TableroDto tableroDto);
}
