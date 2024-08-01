package com.example.AdsCampaign.controller;

import com.example.AdsCampaign.model.CampaignMetrics;
import com.example.AdsCampaign.service.CampaignMetricsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class CampaignMetricsControllerTest {

    @Mock
    private CampaignMetricsService campaignMetricsService;

    @InjectMocks
    private CampaignMetricsController campaignMetricsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMetricsByCampaignId() {
        String campaignId = "testId";
        when(campaignMetricsService.getMetricsByCampaignId(campaignId, "spend", true)).thenReturn(Collections.singletonList(new CampaignMetrics()));
        List<CampaignMetrics> metrics = campaignMetricsController.getMetricsByCampaignId(campaignId, "spend", true);
        assertFalse(metrics.isEmpty());
        verify(campaignMetricsService, times(1)).getMetricsByCampaignId(campaignId, "spend", true);
    }
}
