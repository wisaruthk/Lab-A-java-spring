package com.wk.lab04.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="service_request")
public class ServiceRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String subject;
	
	
	@Column
	private LocalDateTime requestOn;
	
	@Column
	private String customerId;
	
	@Column 
	private String customerName;
	
	@Column
	@Enumerated(EnumType.STRING)
	private ServiceRequestStatus statusCode;
	

	@Column 
	private LocalDateTime resolvedOn;
	
	@Column
	private LocalDateTime createdOn;
	
	@Column
	private LocalDateTime modifiedOn;
	
	@PrePersist
    protected void onCreate() {
        this.createdOn = LocalDateTime.now();
        this.modifiedOn = LocalDateTime.now();
    }
	
	@PreUpdate
	protected void onUpdate() {
		this.modifiedOn = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public LocalDateTime getRequestOn() {
		return requestOn;
	}

	public void setRequestOn(LocalDateTime requestOn) {
		this.requestOn = requestOn;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public ServiceRequestStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(ServiceRequestStatus statusCode) {
		this.statusCode = statusCode;
	}

	public LocalDateTime getResolvedOn() {
		return resolvedOn;
	}

	public void setResolvedOn(LocalDateTime resolvedOn) {
		this.resolvedOn = resolvedOn;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
	
}
