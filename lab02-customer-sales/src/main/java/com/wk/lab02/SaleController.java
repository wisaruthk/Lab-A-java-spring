package com.wk.lab02;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/sales")
public class SaleController {
	
	private final SaleRepository saleRepository;
	
	
	public SaleController(SaleRepository saleRepo) {
		this.saleRepository = saleRepo;
	}
	
	

}
