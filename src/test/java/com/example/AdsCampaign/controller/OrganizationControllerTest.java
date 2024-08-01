package com.example.AdsCampaign.controller;

import com.example.AdsCampaign.model.Organization;
import com.example.AdsCampaign.service.OrganizationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrganizationController.class)
class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrganizationService organizationService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOrganizations() throws Exception {
        Organization organization = new Organization();
        when(organizationService.getAllOrganizations()).thenReturn(List.of(organization));

        mockMvc.perform(get("/organizations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").isNotEmpty());

        verify(organizationService, times(1)).getAllOrganizations();
    }

    @Test
    void testGetOrganizationById() throws Exception {
        String id = "testId";
        Organization organization = new Organization();
        organization.setId(id); // Set the ID to ensure it is included in the response
        organization.setName("Test Organization"); // Set other fields if needed
        organization.setStatus("Active");

        when(organizationService.getOrganizationById(id)).thenReturn(organization);

        mockMvc.perform(get("/organizations/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))  // Check if ID is as expected
                .andExpect(jsonPath("$.name").value("Test Organization"))  // Optional: Check other fields
                .andExpect(jsonPath("$.status").value("Active")); // Optional: Check other fields

        verify(organizationService, times(1)).getOrganizationById(id);
    }

    @Test
    void testGetOrganizationById_NotFound() throws Exception {
        String id = "nonExistentId";
        when(organizationService.getOrganizationById(id)).thenThrow(new RuntimeException("Organization not found"));

        mockMvc.perform(get("/organizations/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Organization not found"));

        verify(organizationService, times(1)).getOrganizationById(id);
    }

    @Test
    void testAddOrganization() throws Exception {
        Organization organization = new Organization();
        when(organizationService.addOrganization(any(Organization.class))).thenReturn(false);

        mockMvc.perform(post("/organizations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(organization)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Organization added successfully."));

        verify(organizationService, times(1)).addOrganization(any(Organization.class));
    }

    @Test
    void testAddOrganization_BadRequest() throws Exception {
        Organization organization = new Organization();
        when(organizationService.addOrganization(any(Organization.class))).thenThrow(new RuntimeException("Organization already exists"));

        mockMvc.perform(post("/organizations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(organization)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Organization already exists"));

        verify(organizationService, times(1)).addOrganization(any(Organization.class));
    }

    @Test
    void testUpdateOrganization() throws Exception {
        String id = "testId";
        String name = "Updated Name";
        String status = "Updated Status";

        mockMvc.perform(put("/organizations/{id}", id)
                        .param("name", name)
                        .param("status", status))
                .andExpect(status().isOk())
                .andExpect(content().string("Organization updated successfully."));

        verify(organizationService, times(1)).updateOrganization(eq(id), any(Organization.class));
    }

    @Test
    void testUpdateOrganization_BadRequest() throws Exception {
        String id = "nonExistentId";
        String name = "Updated Name";
        String status = "Updated Status";
        doThrow(new RuntimeException("Organization not found")).when(organizationService).updateOrganization(eq(id), any(Organization.class));

        mockMvc.perform(put("/organizations/{id}", id)
                        .param("name", name)
                        .param("status", status))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Organization not found"));

        verify(organizationService, times(1)).updateOrganization(eq(id), any(Organization.class));
    }

    @Test
    void testDeleteOrganization() throws Exception {
        String id = "testId";
        doNothing().when(organizationService).deleteOrganization(id);

        mockMvc.perform(delete("/organizations/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Organization deleted successfully."));

        verify(organizationService, times(1)).deleteOrganization(id);
    }

    @Test
    void testDeleteOrganization_NotFound() throws Exception {
        String id = "nonExistentId";
        doThrow(new RuntimeException("Organization not found")).when(organizationService).deleteOrganization(id);

        mockMvc.perform(delete("/organizations/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Organization not found"));

        verify(organizationService, times(1)).deleteOrganization(id);
    }
}
