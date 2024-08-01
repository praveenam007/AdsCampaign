package com.example.AdsCampaign.controller;

import com.example.AdsCampaign.model.Campaign;
import com.example.AdsCampaign.service.CampaignService;
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
import static org.mockito.Mockito.*;

class CampaignControllerTest {

    @Mock
    private CampaignService campaignService;

    @InjectMocks
    private CampaignController campaignController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCampaigns() {
        when(campaignService.getAllCampaigns()).thenReturn(Collections.singletonList(new Campaign()));
        List<Campaign> campaigns = campaignController.getAllCampaigns();
        assertEquals(1, campaigns.size());
        verify(campaignService, times(1)).getAllCampaigns();
    }

    @Test
    void testGetCampaignById() {
        String id = "testId";
        Campaign campaign = new Campaign();
        when(campaignService.getCampaignById(id)).thenReturn(campaign);
        ResponseEntity<Campaign> response = campaignController.getCampaignById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(campaign, response.getBody());
        verify(campaignService, times(1)).getCampaignById(id);
    }

    @Test
    void testAddCampaign() {
        Campaign campaign = new Campaign();
        ResponseEntity<String> response = campaignController.addCampaign(campaign);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(campaignService, times(1)).addCampaign(campaign);
    }

    @Test
    void testUpdateCampaign() {
        String id = "testId";
        Campaign campaign = new Campaign();
        ResponseEntity<String> response = campaignController.updateCampaign(id, campaign);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(campaignService, times(1)).updateCampaign(id, campaign);
    }
}
