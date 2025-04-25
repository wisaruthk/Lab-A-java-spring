package com.wk.lab04.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wk.lab04.dto.ServiceRequestStatusDTO;
import com.wk.lab04.entity.ServiceRequest;
import com.wk.lab04.entity.ServiceRequestStatus;


import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
//@WireMockTest(httpPort = 8082)
class ServiceRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.hikari.max-lifetime",()->"20000");
        registry.add("spring.datasource.hikari.idle-timeout", ()->"10000");
    }
    
    
    @Test
    @Order(1)
    void testCreateServiceRequest() throws Exception {
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setSubject("Credit card amount for payment");
        serviceRequest.setCustomerName("Sombat Goodwood");
        

        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(serviceRequest);

        mockMvc.perform(post("/service-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.statusCode").value("IN_PROGRESS"));
    }
    
    
    @Test
    @Order(2)
    void testForwardIssueToBackOffice() throws Exception {
    	// Trigger to another microservice
    	mockMvc.perform(post("/service-requests/1/forward")
    			.contentType(MediaType.APPLICATION_JSON)
    			)
    	.andExpect(status().isOk())
    	;
    }
    
    @Test
    @Order(3)
    void testUpdateStatus_COMPLETED() throws Exception {
    	// Receive Trigger to another microservice
    	ServiceRequestStatusDTO dto = new ServiceRequestStatusDTO();
    	dto.setStatusCode(ServiceRequestStatus.COMPLETED);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonContent = mapper.writeValueAsString(dto);
    	
    	mockMvc.perform(patch("/service-requests/1/status")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(jsonContent)
    			)
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$.statusCode").value("COMPLETED"))
    	;
    }
   

    
}