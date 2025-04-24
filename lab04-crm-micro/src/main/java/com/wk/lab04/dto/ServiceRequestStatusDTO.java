package com.wk.lab04.dto;

import com.wk.lab04.entity.ServiceRequestStatus;

public class ServiceRequestStatusDTO {
	private ServiceRequestStatus statusCode;
	private String message;
	
	public ServiceRequestStatusDTO() {
		
	}

	public ServiceRequestStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(ServiceRequestStatus statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
