package com.wk.lab04.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wk.lab04.dto.ServiceRequestStatusDTO;
import com.wk.lab04.entity.ServiceRequest;
import com.wk.lab04.entity.ServiceRequestStatus;
import com.wk.lab04.repository.ServiceRequestRepository;
import org.springframework.web.bind.annotation.RequestParam;
import com.wk.lab04.service.BackOfficeServiceClient;;



@RestController
@RequestMapping("/service-requests")
public class ServiceRequestController {

	private final ServiceRequestRepository srRepository;
    private final BackOfficeServiceClient backOffice;
	
	public ServiceRequestController(ServiceRequestRepository srRepo, BackOfficeServiceClient backOffice) {
		this.srRepository = srRepo;
        this.backOffice = backOffice;
	}

	
	@PostMapping
	public ResponseEntity<ServiceRequest> createServiceRequest(@RequestBody ServiceRequest serviceRequest) {
		serviceRequest.setStatusCode(ServiceRequestStatus.IN_PROGRESS);
        serviceRequest.setRequestOn(LocalDateTime.now());
        
		ServiceRequest addedServiceRequest = srRepository.save(serviceRequest);

		return new ResponseEntity<>(addedServiceRequest, HttpStatus.CREATED);
		
	}

    @GetMapping
    public List<ServiceRequest> getAll() {
        return srRepository.findAll().stream()
			.collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRequest> getOne(@PathVariable Long id) {
        Optional<ServiceRequest> sr = srRepository.findById(id);
        if (sr.isPresent()) {
            return new ResponseEntity<>(sr.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ServiceRequest> updateStatus(
    		@PathVariable Long id, 
    		@RequestBody ServiceRequestStatusDTO serviceRequestDTO) {
    	
        ServiceRequest sr = srRepository.findById(id)
        		.orElseThrow(() -> new RuntimeException("ServiceRequest not found"));
        
        sr.setStatusCode(serviceRequestDTO.getStatusCode());
        if(sr.getStatusCode() == ServiceRequestStatus.COMPLETED) {
            sr.setResolvedOn(LocalDateTime.now());
        } else {
            sr.setResolvedOn(null);
        }
        ServiceRequest saved = srRepository.save(sr);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }
    
    @PostMapping("/{id}/forward")
    public ResponseEntity<?> forwardToBackOffice(@PathVariable Long id) {
    	ServiceRequest sr = srRepository.findById(id)
    			.orElseThrow(() -> new RuntimeException("ServiceRequest not found"));
    	
    	// Send Event to next process
    	backOffice.send(sr);
        
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
	
}
