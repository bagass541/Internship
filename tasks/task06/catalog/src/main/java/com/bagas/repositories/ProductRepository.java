package com.bagas.repositories;

import com.bagas.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = "description")
    List<Product> findBySubcategory_id(Long id, Pageable pageable);
}
