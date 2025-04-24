package com.wk.lab04.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wk.lab04.entity.ServiceRequest;

@Service
public class BackOfficeServiceClient {

    private final RestTemplate restTemplate;

    public BackOfficeServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String send(ServiceRequest serviceRequest) {
        System.out.println("===Forward to next service:"+serviceRequest.getId());
        String url = "http://localhost:8082/api/endpoint"; 
        return restTemplate.postForObject(url, serviceRequest, String.class);
    }
}