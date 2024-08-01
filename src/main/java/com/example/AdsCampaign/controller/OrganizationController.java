package com.example.AdsCampaign.controller;

import com.example.AdsCampaign.model.Organization;
import com.example.AdsCampaign.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping
    public List<Organization> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrganizationById(@PathVariable String id) {
        try {
            Organization organization = organizationService.getOrganizationById(id);
            if (organization == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Organization not found");
            }
            return ResponseEntity.ok(organization);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Organization not found");
        }
    }


    @PostMapping
    public ResponseEntity<String> addOrganization(@RequestBody Organization organization) {
        try {
            organizationService.addOrganization(organization);
            return ResponseEntity.status(HttpStatus.CREATED).body("Organization added successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrganization(
            @PathVariable String id,
            @RequestParam String name,
            @RequestParam String status
    ) {
        try {
            Organization updatedOrganization = new Organization();
            updatedOrganization.setName(name);
            updatedOrganization.setStatus(status);
            organizationService.updateOrganization(id, updatedOrganization);
            return ResponseEntity.ok("Organization updated successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrganization(@PathVariable String id) {
        try {
            organizationService.deleteOrganization(id);
            return ResponseEntity.ok("Organization deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
