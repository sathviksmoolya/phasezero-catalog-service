package com.jsp.phasezero_catalog_service.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
	
	
	@NotBlank(message = "partnumber cannot be empty")
	String partNumber;
	
	@NotBlank(message = "partname cannot be empty")
	String partName;
	
	@NotBlank(message = "category cannot be empty")
	String category;
	
	@Min(value = 0 , message = "price cannot be negative")
	Double price;
	
	@Min(value = 0 , message = "stock cannot be negative")
	Integer stock;
	
	
	

}
