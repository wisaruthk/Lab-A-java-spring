package com.wk.lab02;

import java.time.LocalDate;
import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="sale")
public class Sale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private Double saleAmount;
	
	@Column
	private LocalDateTime saleDate;
	
	@Nonnull
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	public Sale() {
		
	}
	
	public Sale(Double saleAmount, LocalDateTime saleDate, Customer customer) {
		this.saleAmount = saleAmount;
		this.saleDate = saleDate;
		this.customer = customer;
	}

	public Double getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(Double saleAmount) {
		this.saleAmount = saleAmount;
	}

	public LocalDateTime getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDateTime saleDate) {
		this.saleDate = saleDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}
	
	
	
}