package com.ejemplo.sopadeletras.repository;

import com.ejemplo.sopadeletras.persistense.Tablero;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface TableroRepository extends PagingAndSortingRepository<Tablero, UUID> {
    Tablero findTableroById(UUID id);
    boolean existsTableroById(UUID id);
}
