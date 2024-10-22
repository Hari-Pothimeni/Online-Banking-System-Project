package org.jpmc.OBS.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "branch")
public class Branch {
    
    @Id
    @Column(name = "branch_id")
    private String branchId;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "ifsc_code")
    private String ifscCode;

    // Many branches can be associated with one admin
    @ManyToOne
    @JoinColumn(name = "admin_id") // Ensures that admin is not null for a branch
    private Admin admin;

    // Constructors, Getters, and Setters
    
    public Branch() {
    }

    public Branch(String branchId, String name, String location, String ifscCode, Admin admin) {
        this.branchId = branchId;
        this.name = name;
        this.location = location;
        this.ifscCode = ifscCode;
        this.admin = admin;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
