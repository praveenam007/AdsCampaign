package com.example.AdsCampaign.repository;

import com.example.AdsCampaign.model.CampaignMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignMetricsRepository extends JpaRepository<CampaignMetrics, String> {
    List<CampaignMetrics> findByCampaignId(String campaignId);
}
