package com.example.AdsCampaign.service;

import com.example.AdsCampaign.model.Organization;
import com.example.AdsCampaign.repository.OrganizationRepository;
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

class OrganizationServiceTest {

    @Mock
    private OrganizationRepository organizationRepository;

    @InjectMocks
    private OrganizationService organizationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOrganizations() {
        when(organizationRepository.findAll()).thenReturn(Collections.singletonList(new Organization()));
        List<Organization> organizations = organizationService.getAllOrganizations();
        assertFalse(organizations.isEmpty());
        verify(organizationRepository, times(1)).findAll();
    }

    @Test
    void testGetOrganizationById() {
        String id = "testId";
        Organization organization = new Organization();
        when(organizationRepository.findById(id)).thenReturn(Optional.of(organization));
        Organization found = organizationService.getOrganizationById(id);
        assertEquals(organization, found);
        verify(organizationRepository, times(1)).findById(id);
    }

    @Test
    void testAddOrganization() {
        Organization organization = new Organization();
        when(organizationRepository.existsById(organization.getId())).thenReturn(false); // Ensure it doesn't exist
        organizationService.addOrganization(organization);
        verify(organizationRepository, times(1)).save(organization);
    }

    @Test
    void testUpdateOrganization() {
        String id = "testId";
        String name = "New Organization Name";
        String status = "Active";

        Organization existingOrganization = new Organization();
        existingOrganization.setId(id);

        Organization updatedOrganization = new Organization();
        updatedOrganization.setId(id); // Set the id to match the existing one
        updatedOrganization.setName(name);
        updatedOrganization.setStatus(status);

        // Mock the behavior of repository
        when(organizationRepository.existsById(id)).thenReturn(true);
        when(organizationRepository.findById(id)).thenReturn(Optional.of(existingOrganization));

        organizationService.updateOrganization(id, updatedOrganization);

        // Verify that the save method was called with the updatedOrganization
        verify(organizationRepository, times(1)).save(updatedOrganization);
    }

    @Test
    void testUpdateOrganization_NotFound() {
        String id = "nonExistentId";
        Organization updatedOrganization = new Organization();
        updatedOrganization.setId(id);

        when(organizationRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () ->
                organizationService.updateOrganization(id, updatedOrganization)
        );
        assertEquals("Organization not found", thrown.getMessage());
    }
}

