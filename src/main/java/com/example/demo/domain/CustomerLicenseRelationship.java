package com.example.demo.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by HurleyD on 22/08/2017.
 */
@RelationshipEntity(type = "CONTAINS")
public class CustomerLicenseRelationship {

    @GraphId
    private Long nodeId;

    @StartNode
    Customer customer;

    @EndNode
    License license;

    public CustomerLicenseRelationship() {
    }

    public CustomerLicenseRelationship(Customer customer, License license) {
        this.customer = customer;
        this.license = license;
    }

    public Long getNodeId() {
        return nodeId;
    }

}
