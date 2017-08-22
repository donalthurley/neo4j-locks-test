package com.example.demo.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by HurleyD on 22/08/2017.
 */
@NodeEntity
public class License {

    @GraphId
    private Long nodeId;

    String licenseNumber;

    @Relationship(type = "MAPPED", direction = Relationship.OUTGOING)
    private LicenseProductRelationship mapped;

    public LicenseProductRelationship getMapped() {
        return mapped;
    }

    public void setMapped(LicenseProductRelationship mapped) {
        this.mapped = mapped;
    }

    public License() {
    }

    public License(Long nodeId) {
        this.nodeId = nodeId;
    }

    public License(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
