package com.wk.lab02;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;


@Entity
@Table(name="customer")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private LocalDate customerDate;
	
	@Column
	private boolean isVIP;
	
	@Column
	@Enumerated(EnumType.STRING)
	private CustomerStatus statusCode;
	
	@Column
	private LocalDateTime createdOn;
	
	@Column
	private LocalDateTime modifiedOn;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Sale> sales;
	
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getCustomerDate() {
		return customerDate;
	}

	public void setCustomerDate(LocalDate customerDate) {
		this.customerDate = customerDate;
	}

	public boolean isVIP() {
		return isVIP;
	}

	public void setVIP(boolean isVIP) {
		this.isVIP = isVIP;
	}

	public CustomerStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(CustomerStatus statusCode) {
		this.statusCode = statusCode;
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

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	public Long getId() {
		return id;
	}

	public Customer(String firstName, String lastName, CustomerStatus status, boolean isVIP) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.statusCode = status;
		this.isVIP = isVIP;
	}
	
	public Customer() {
		// FOR JPA
	}
	
	
	@PrePersist
    protected void onCreate() {
        this.createdOn = LocalDateTime.now();
        this.modifiedOn = LocalDateTime.now();
    }
	
	@PreUpdate
	protected void onUpdate() {
		this.modifiedOn = LocalDateTime.now();
	}
	
}
