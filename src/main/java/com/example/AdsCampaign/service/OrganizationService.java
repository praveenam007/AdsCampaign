package com.example.AdsCampaign.service;

import com.example.AdsCampaign.model.Organization;
import com.example.AdsCampaign.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.lang.RuntimeException;

@Service
public class OrganizationService {

    private final OrganizationRepository repository;

    public OrganizationService(OrganizationRepository repository) {
        this.repository = repository;
    }

    public List<Organization> getAllOrganizations() {
        return repository.findAll();
    }

    public Organization getOrganizationById(String id) {
        return repository.findById(id).orElse(null);
    }

    public boolean addOrganization(Organization organization) {
        if (repository.existsById(organization.getId())) {
            return true; // Organization already exists
        } else {
            repository.save(organization);
            return false; // Organization created
        }
    }

    public void updateOrganization(String id, Organization updatedOrganization) {
        if (repository.existsById(id)) {
            updatedOrganization.setId(id);
            repository.save(updatedOrganization);
        } else {
            throw new RuntimeException("Organization not found");
        }
    }

    public boolean updateOrganizationStatus(String id, String status) {
        if (repository.existsById(id)) {
            Organization organization = repository.findById(id).get();
            organization.setStatus(status);
            repository.save(organization);
            return true; // Status updated
        } else {
            return false; // Organization not found
        }
    }

    public void deleteOrganization(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Organization not found");
        }
    }
}
