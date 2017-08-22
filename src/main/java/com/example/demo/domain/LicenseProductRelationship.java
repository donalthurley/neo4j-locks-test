package com.example.demo.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by HurleyD on 22/08/2017.
 */
@RelationshipEntity(type = "MAPPED")
public class LicenseProductRelationship {

    @GraphId
    private Long nodeId;

    @StartNode
    License license;

    @EndNode
    Product product;


    public LicenseProductRelationship() {
    }

    public LicenseProductRelationship(License license, Product product) {
        this.license = license;
        this.product = product;
    }

    public Long getNodeId() {
        return nodeId;
    }

}
