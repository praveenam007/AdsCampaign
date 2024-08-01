package com.example.AdsCampaign.service;

import com.example.AdsCampaign.model.CampaignMetrics;
import com.example.AdsCampaign.repository.CampaignMetricsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CampaignMetricsServiceTest {

    @Mock
    private CampaignMetricsRepository campaignMetricsRepository;

    @InjectMocks
    private CampaignMetricsService campaignMetricsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMetricsByCampaignId() {
        String campaignId = "testId";
        when(campaignMetricsRepository.findByCampaignId(campaignId)).thenReturn(Collections.singletonList(new CampaignMetrics()));
        List<CampaignMetrics> metrics = campaignMetricsService.getMetricsByCampaignId(campaignId, "spend", true);
        assertFalse(metrics.isEmpty());
        verify(campaignMetricsRepository, times(1)).findByCampaignId(campaignId);
    }
}
