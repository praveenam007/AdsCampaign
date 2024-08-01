package com.example.AdsCampaign.service;

import com.example.AdsCampaign.model.Campaign;
import com.example.AdsCampaign.repository.CampaignRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    private final CampaignRepository repository;

    public CampaignService(CampaignRepository repository) {
        this.repository = repository;
    }

    public List<Campaign> getAllCampaigns() {
        return repository.findAll();
    }

    public Campaign getCampaignById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Campaign not found"));
    }

    public void addCampaign(Campaign campaign) {
        if (repository.existsById(campaign.getId())) {
            throw new RuntimeException("Campaign already exists");
        } else {
            repository.save(campaign);
        }
    }

    public void updateCampaign(String id, Campaign updatedCampaign) {
        if (repository.existsById(id)) {
            updatedCampaign.setId(id);
            repository.save(updatedCampaign);
        } else {
            throw new RuntimeException("Campaign not found");
        }
    }
}
