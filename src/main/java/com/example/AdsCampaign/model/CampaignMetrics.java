package com.example.AdsCampaign.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "campaign_metrics")
public class CampaignMetrics {

    @Id
    private String id;
    private String campaignId;
    private double spend;
    private double revenue;
    private int impressions;
    private int clicks;

    // Constructors, getters, and setters
    public CampaignMetrics() {}

    public CampaignMetrics(String id, String campaignId, double spend, double revenue, int impressions, int clicks) {
        this.id = id;
        this.campaignId = campaignId;
        this.spend = spend;
        this.revenue = revenue;
        this.impressions = impressions;
        this.clicks = clicks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public double getSpend() {
        return spend;
    }

    public void setSpend(double spend) {
        this.spend = spend;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public int getImpressions() {
        return impressions;
    }

    public void setImpressions(int impressions) {
        this.impressions = impressions;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }
}
