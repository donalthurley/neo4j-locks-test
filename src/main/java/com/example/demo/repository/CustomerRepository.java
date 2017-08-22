package com.example.demo.repository;

import com.example.demo.domain.Customer;
import com.example.demo.domain.License;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by HurleyD on 22/08/2017.
 */
public interface CustomerRepository extends GraphRepository<Customer> {

    public Customer findByCustomerNumber(String customerNumber);

    @Query("MATCH (c:Customer) WHERE ID(c) = {id} SET c._lock = 1")
    public void setSerializableLock(@Param("id") Long id);

    @Query("MATCH (c:Customer) WHERE ID(c) = {id} REMOVE c._lock")
    public void removeLock(@Param("id") Long id);

}
