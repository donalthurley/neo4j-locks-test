package com.example.demo.repository;

import com.example.demo.domain.Product;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by HurleyD on 22/08/2017.
 */
public interface ProductRepository extends GraphRepository<Product> {

    public Product findByIsbn(String isbn);

    @Query("MATCH (p:Product) WHERE ID(p) = {id} SET p._lock = 1")
    public void setSerializableLock(@Param("id") Long id);

    @Query("MATCH (p:Product) WHERE ID(p) = {id} REMOVE p._lock")
    public void removeLock(@Param("id") Long id);

}
