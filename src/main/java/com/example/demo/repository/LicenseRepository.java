package com.example.demo.repository;

import com.example.demo.domain.Customer;
import com.example.demo.domain.License;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by HurleyD on 22/08/2017.
 */
public interface LicenseRepository  extends GraphRepository<License> {

    @Query("MATCH (c:Customer {customerNumber: {customerNumber}})-[:CONTAINS]->(l:License)-[mapped:MAPPED]->(p:Product {isbn: {isbn}}) RETURN l,mapped,p")
    public License findLicense(@Param("customerNumber") String customerNumber, @Param("isbn") String isbn);

}
