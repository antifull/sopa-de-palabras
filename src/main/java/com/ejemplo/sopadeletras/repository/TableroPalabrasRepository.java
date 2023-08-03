package com.ejemplo.sopadeletras.repository;

import com.ejemplo.sopadeletras.persistense.Tablero;
import com.ejemplo.sopadeletras.persistense.TableroPalabras;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface TableroPalabrasRepository extends PagingAndSortingRepository<TableroPalabras, UUID> {
    List<TableroPalabras> findAllByTablero(Tablero tablero);
}
