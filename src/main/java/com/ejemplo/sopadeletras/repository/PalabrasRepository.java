package com.ejemplo.sopadeletras.repository;

import com.ejemplo.sopadeletras.persistense.Palabras;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PalabrasRepository extends PagingAndSortingRepository<Palabras, Long> {
}
