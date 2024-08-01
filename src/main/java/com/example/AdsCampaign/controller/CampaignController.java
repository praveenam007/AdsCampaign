package com.example.AdsCampaign.controller;

import com.example.AdsCampaign.model.Campaign;
import com.example.AdsCampaign.service.CampaignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping
    public List<Campaign> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable String id) {
        try {
            Campaign campaign = campaignService.getCampaignById(id);
            return ResponseEntity.ok(campaign);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> addCampaign(@RequestBody Campaign campaign) {
        try {
            campaignService.addCampaign(campaign);
            return ResponseEntity.status(HttpStatus.CREATED).body("Campaign added successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCampaign(@PathVariable String id, @RequestBody Campaign updatedCampaign) {
        try {
            campaignService.updateCampaign(id, updatedCampaign);
            return ResponseEntity.ok("Campaign updated successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
