package com.jsp.phasezero_catalog_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
	
	Integer id;
	
	String partNumber;
	
	String partName;
	
	String category;
	
	Double price;
	
	Integer stock;

}
