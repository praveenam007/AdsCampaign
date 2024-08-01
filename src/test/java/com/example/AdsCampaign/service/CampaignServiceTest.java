package com.example.AdsCampaign.service;

import com.example.AdsCampaign.model.Campaign;
import com.example.AdsCampaign.repository.CampaignRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CampaignServiceTest {

    @Mock
    private CampaignRepository campaignRepository;

    @InjectMocks
    private CampaignService campaignService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCampaigns() {
        when(campaignRepository.findAll()).thenReturn(Collections.singletonList(new Campaign()));
        List<Campaign> campaigns = campaignService.getAllCampaigns();
        assertFalse(campaigns.isEmpty());
        verify(campaignRepository, times(1)).findAll();
    }

    @Test
    void testGetCampaignById() {
        String id = "testId";
        Campaign campaign = new Campaign();
        when(campaignRepository.findById(id)).thenReturn(Optional.of(campaign));
        Campaign found = campaignService.getCampaignById(id);
        assertEquals(campaign, found);
        verify(campaignRepository, times(1)).findById(id);
    }

    @Test
    void testAddCampaign() {
        Campaign campaign = new Campaign();
        campaignService.addCampaign(campaign);
        verify(campaignRepository, times(1)).save(campaign);
    }

    @Test
    void testUpdateCampaign() {
        String id = "testId";
        Campaign campaign = new Campaign();
        when(campaignRepository.existsById(id)).thenReturn(true);
        campaign.setId(id);
        campaignService.updateCampaign(id, campaign);
        verify(campaignRepository, times(1)).save(campaign);
    }

}
