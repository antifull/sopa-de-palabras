package com.ejemplo.sopadeletras.repository;

import com.ejemplo.sopadeletras.persistense.Palabras;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PalabrasRepository extends PagingAndSortingRepository<Palabras, Long> {
    @Query(value="SELECT id,palabra FROM palabras pl ORDER BY RANDOM() LIMIT ?1",nativeQuery = true)
    List<Palabras> palabrasRandom(@Param("cantidad") int cantidad);
}
