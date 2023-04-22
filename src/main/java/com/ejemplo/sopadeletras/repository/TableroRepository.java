package com.ejemplo.sopadeletras.repository;

import com.ejemplo.sopadeletras.persistense.Tablero;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TableroRepository extends PagingAndSortingRepository<Tablero, Long> {
    Tablero findTableroById(Long id);
}
