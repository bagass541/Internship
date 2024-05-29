package com.bagas.repositories.interfaces;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T> {

    List<T> findAll();

    Optional<T> findById(Long id);

    void save(T entity);

    void update(T entity);

    void deleteById(Long id);
}
