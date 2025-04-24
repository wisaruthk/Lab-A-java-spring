package com.wk.lab04.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wk.lab04.dto.CustomerDTO;
import com.wk.lab04.entity.ServiceRequest;

@Service
public class CustomerServiceClient {

    private final RestTemplate restTemplate;

    public CustomerServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<CustomerDTO> verifyUser(String customerId) {
        CustomerDTO mockup = new CustomerDTO(); 
        mockup.setId(customerId);
        mockup.setFullName("Fullname");
        return Optional.of(mockup);
        
    }
}