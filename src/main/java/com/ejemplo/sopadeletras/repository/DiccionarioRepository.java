package com.ejemplo.sopadeletras.repository;

import com.ejemplo.sopadeletras.persistense.Diccionario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DiccionarioRepository extends PagingAndSortingRepository<Diccionario, Long> {
}
