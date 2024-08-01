package com.example.AdsCampaign.controller;

import com.example.AdsCampaign.model.CampaignMetrics;
import com.example.AdsCampaign.service.CampaignMetricsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metrics")
public class CampaignMetricsController {

    private final CampaignMetricsService service;

    public CampaignMetricsController(CampaignMetricsService service) {
        this.service = service;
    }

    @GetMapping("/{campaignId}")
    public List<CampaignMetrics> getMetricsByCampaignId(
            @PathVariable String campaignId,
            @RequestParam(required = false, defaultValue = "spend") String sortBy,
            @RequestParam(required = false, defaultValue = "false") boolean asc
    ) {
        return service.getMetricsByCampaignId(campaignId, sortBy, asc);
    }

    @PostMapping
    public void addMetrics(@RequestBody CampaignMetrics metrics) {
        service.addMetrics(metrics);
    }
}
