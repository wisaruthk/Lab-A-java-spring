package com.wk.lab04.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.wk.lab04.entity.ServiceRequest;
import com.wk.lab04.entity.ServiceRequestStatus;


@RepositoryRestResource(exported = false)
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

	
	public List<ServiceRequest> findByStatusCode(ServiceRequestStatus statusCode); 
}
