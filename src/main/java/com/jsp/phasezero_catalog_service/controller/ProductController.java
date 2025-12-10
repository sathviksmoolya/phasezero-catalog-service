package com.jsp.phasezero_catalog_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.phasezero_catalog_service.dto.ProductRequest;
import com.jsp.phasezero_catalog_service.dto.ProductResponse;
import com.jsp.phasezero_catalog_service.entity.Product;
import com.jsp.phasezero_catalog_service.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping("/add")
	ResponseEntity<ProductResponse> add( @Valid @RequestBody ProductRequest productRequest){
		ProductResponse saved = productService.addProduct(productRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	@GetMapping("/allproducts")
		ResponseEntity<List<ProductResponse>> getProducts(){
			return ResponseEntity.ok(productService.getAllProducts()) ;
		
	}
	
	@GetMapping("/search")
	ResponseEntity<List<ProductResponse>> getFilteredProducts(@RequestParam String name){
		return ResponseEntity.ok(productService.filterBypartName(name));
	}
	
	@GetMapping("/category/{category}")
	ResponseEntity<List<ProductResponse>> getCategoryFilteredProduct(@PathVariable String category){
		return ResponseEntity.ok(productService.filterByCategory(category));
	}
	
	@GetMapping("/sort/price")
	ResponseEntity<List<ProductResponse>> getSortByPrice(){
		return ResponseEntity.ok(productService.sortByPrice());
	}
	
	@GetMapping("/inventory/value")
	ResponseEntity<Double> getTotalValueOfProducts(){
		return ResponseEntity.ok(productService.getInventorySum());
	}
	
	
	
		
	
	

}
