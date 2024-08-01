package com.example.AdsCampaign.service;

import com.example.AdsCampaign.model.CampaignMetrics;
import com.example.AdsCampaign.repository.CampaignMetricsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignMetricsService {

    private final CampaignMetricsRepository repository;

    public CampaignMetricsService(CampaignMetricsRepository repository) {
        this.repository = repository;
    }

    public List<CampaignMetrics> getMetricsByCampaignId(String campaignId, String sortBy, boolean asc) {
        List<CampaignMetrics> metrics = repository.findByCampaignId(campaignId);

        // Sorting based on sortBy parameter
        metrics.sort((m1, m2) -> {
            int comparison = 0;
            switch (sortBy) {
                case "spend":
                    comparison = Double.compare(m1.getSpend(), m2.getSpend());
                    break;
                case "revenue":
                    comparison = Double.compare(m1.getRevenue(), m2.getRevenue());
                    break;
                case "impressions":
                    comparison = Integer.compare(m1.getImpressions(), m2.getImpressions());
                    break;
                case "clicks":
                    comparison = Integer.compare(m1.getClicks(), m2.getClicks());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid sort field: " + sortBy);
            }
            return asc ? comparison : -comparison;
        });

        return metrics;
    }

    public void addMetrics(CampaignMetrics metrics) {
        repository.save(metrics);
    }
}
