package com.wk.lab02.config;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wk.lab02.Customer;
import com.wk.lab02.CustomerRepository;
import com.wk.lab02.CustomerStatus;
import com.wk.lab02.Sale;
import com.wk.lab02.SaleRepository;

@Configuration
public class DatabaseLoader {
	/*
	 * MOCK UP Data
	 */
	
	
	@Bean
	CommandLineRunner init(
			CustomerRepository custRepo,
			SaleRepository saleRepo
			) {
		return args -> {
			Customer john = new Customer("John","Wick",CustomerStatus.ACTIVE,true);
			Customer adam = new Customer("Adam","Han",CustomerStatus.ACTIVE,true);
			Customer somchai = new Customer("Somchai","Sawasdee",CustomerStatus.ACTIVE,true);
			custRepo.saveAll(List.of(john,adam,somchai));
			
			saleRepo.saveAll(List.of(
					// 2025
					// John transaction
					new Sale(230.5,LocalDateTime.now(),john),
					new Sale(350.50,LocalDateTime.now(),john),
					new Sale(500.50,LocalDateTime.now(),john),
					new Sale(2000.50,LocalDateTime.now(),john),
					// Adam transaction
					new Sale(1500.00,LocalDateTime.now(),adam),
					// Somchai transaction
					new Sale(1500.00,LocalDateTime.now(),somchai),
					new Sale(1500.00,LocalDateTime.now(),somchai),
					new Sale(500.00,LocalDateTime.now(),somchai),
					// 2024
					// John transaction [1_550]
					new Sale(700.0,LocalDateTime.of(LocalDate.of(2024, 4, 5), LocalTime.now()),john),
					new Sale(350.0,LocalDateTime.of(LocalDate.of(2024, 4, 5), LocalTime.now()),john),
					new Sale(500.0,LocalDateTime.of(LocalDate.of(2024, 4, 5), LocalTime.now()),john),
					// Adam transaction [1_300]
					new Sale(1300.0,LocalDateTime.of(LocalDate.of(2024, 4, 5), LocalTime.now()),adam),
					// Somchai transaction [1_745]
					new Sale(1245.0,LocalDateTime.of(LocalDate.of(2024, 4, 5), LocalTime.now()),somchai),
					new Sale(500.0,LocalDateTime.of(LocalDate.of(2024, 4, 5), LocalTime.now()),somchai)
					));
		};
	}
}
