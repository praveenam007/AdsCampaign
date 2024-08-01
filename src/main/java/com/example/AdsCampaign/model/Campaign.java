package com.example.AdsCampaign.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "campaigns")
public class Campaign {

    @Id
    private String id;
    private String name;
    private String status;
    private double budget;

    // Constructors, getters, and setters
    public Campaign() {}

    public Campaign(String id, String name, String status, double budget) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.budget = budget;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
