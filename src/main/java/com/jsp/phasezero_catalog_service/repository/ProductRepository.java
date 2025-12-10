package com.jsp.phasezero_catalog_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.phasezero_catalog_service.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	Boolean existsBypartNumber(String partNumber);
	
	List<Product>findByPartNameContainingIgnoreCase(String partName);
	
	List<Product>findByCategoryIgnoreCase(String category);
	
	List<Product>findAllByOrderByPriceAsc();

}
