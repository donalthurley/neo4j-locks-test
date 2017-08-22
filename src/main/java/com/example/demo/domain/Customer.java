package com.example.demo.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by HurleyD on 22/08/2017.
 */
@NodeEntity
public class Customer {

    @GraphId
    private Long nodeId;

    String customerNumber;

    @Relationship(type = "CONTAINS", direction = Relationship.OUTGOING)
    private Set<CustomerLicenseRelationship> contains = new HashSet<>();

    public Customer() {
    }

    public Customer(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Customer(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Set<CustomerLicenseRelationship> getContains() {
        return contains;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
