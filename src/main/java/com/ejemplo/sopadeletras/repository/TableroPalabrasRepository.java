package com.ejemplo.sopadeletras.repository;

import com.ejemplo.sopadeletras.persistense.Tablero;
import com.ejemplo.sopadeletras.persistense.TableroPalabras;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TableroPalabrasRepository extends PagingAndSortingRepository<TableroPalabras, Long> {
    List<TableroPalabras> findAllByTablero(Tablero tablero);
}
