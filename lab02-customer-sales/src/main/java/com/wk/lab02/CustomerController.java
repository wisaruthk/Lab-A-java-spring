package com.wk.lab02;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerRepository customerRepository;
	private final SaleRepository saleRepository;
	
	public CustomerController(CustomerRepository repo, SaleRepository saleRepo) {
		this.customerRepository = repo;
		this.saleRepository = saleRepo;
	}
	
	@PostMapping("/{customerId}/sales")
	public ResponseEntity<Sale> createSale(@PathVariable Long customerId, @RequestBody Sale sale) {
		Customer cust = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found"));
		sale.setCustomer(cust);
		sale.setSaleDate(LocalDateTime.now());
		Sale addedSale = saleRepository.save(sale);
		return new ResponseEntity<>(addedSale, HttpStatus.CREATED);
	}
	
	@GetMapping("/{customerId}/sales")
    public ResponseEntity<List<Sale>> getSalesByCustomer(@PathVariable Long customerId) {
        Customer cust = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        List<Sale> sales = saleRepository.findByCustomer(cust);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }
}
