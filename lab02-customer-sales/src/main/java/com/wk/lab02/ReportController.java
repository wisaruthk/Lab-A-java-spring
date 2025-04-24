package com.wk.lab02;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@RestController
@RequestMapping
public class ReportController {
	
	@PersistenceContext
    private EntityManager entityManager;
	

	/**
	 * return as array
	 * [rank, customer_full_name, total_amount]
	 * @param year
	 * @return Array of customer total amount
	 */
    @GetMapping("/reports/total-amount-by-customer/{year}")
    public List<Object[]> getTotalAmountByCustomer(@PathVariable int year) {
        String query = """
            SELECT
        		ROW_NUMBER() OVER (ORDER BY SUM(s.sale_amount) DESC) AS rank,
        		c.first_name ||' '|| c.last_name as customer_full_name, 
        		SUM(s.sale_amount) as total_amount 
            FROM sale s LEFT JOIN customer c ON c.id = s.customer_id 
            WHERE EXTRACT(YEAR FROM s.sale_date) = :year
            GROUP BY c.id, c.first_name
            ORDER BY total_amount DESC
            """;
        
        return entityManager.createNativeQuery(query)
            .setParameter("year", year)
            .getResultList();
    }
    

}
