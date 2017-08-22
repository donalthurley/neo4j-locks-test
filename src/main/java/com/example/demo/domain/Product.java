package com.example.demo.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by HurleyD on 22/08/2017.
 */
@NodeEntity
public class Product {

    @GraphId
    private Long nodeId;

    String isbn;

    public Product() {
    }

    public Product(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Product(String isbn) {
        this.isbn = isbn;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
