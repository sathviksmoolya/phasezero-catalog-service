package com.jsp.phasezero_catalog_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jsp.phasezero_catalog_service.dto.ProductRequest;
import com.jsp.phasezero_catalog_service.dto.ProductResponse;
import com.jsp.phasezero_catalog_service.entity.Product;
import com.jsp.phasezero_catalog_service.repository.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	
	
	public ProductResponse addProduct (ProductRequest productRequest) {
		
		Product product = convertToEntity(productRequest);
		Product saved = productRepository.save(product);
		
		return convertToResponse(saved);
		
	}
	
	public List<ProductResponse> getAllProducts(){
		List<Product> productList = productRepository.findAll();
		ArrayList<ProductResponse> productResponseList = new ArrayList<>();
		
		for(Product p : productList) {
			productResponseList.add(convertToResponse(p));
		}
		
		return productResponseList;
	}
	
	public List<ProductResponse> filterBypartName(String name){
		List<Product> filteredProducts = productRepository.findByPartNameContainingIgnoreCase(name);
		
		ArrayList<ProductResponse> filteredProductsResponse = new ArrayList<>();
		
		for(Product p : filteredProducts) {
			filteredProductsResponse.add(convertToResponse(p));
		}
		
		return filteredProductsResponse;
	}
	
	public List<ProductResponse> filterByCategory(String category){
		List<Product> filteredProducts = productRepository.findByCategoryIgnoreCase(category);
		
		ArrayList<ProductResponse> filteredProductsResponse = new ArrayList<>();
		
		for(Product p : filteredProducts) {
			filteredProductsResponse.add(convertToResponse(p));
		}
		
		return filteredProductsResponse;
		
		
	}
	
	public List<ProductResponse> sortByPrice(){
		List<Product> productList = productRepository.findAllByOrderByPriceAsc();
		
		ArrayList<ProductResponse> productsResponse = new ArrayList<>();
		
		for(Product p : productList) {
			productsResponse.add(convertToResponse(p));
		}
		
		return productsResponse;
	}
	
	public double getInventorySum() {
		List<Product> productList = productRepository.findAll();
		double sum =0;
		for(Product p : productList) {
			
			sum += p.getPrice() * p.getStock();
			
		}
		return sum;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Product convertToEntity(ProductRequest productRequest) {
		Product product = new Product();
		
		product.setPartName(productRequest.getPartName());
		product.setPartNumber(productRequest.getPartNumber());
		product.setCategory(productRequest.getCategory());
		product.setPrice(productRequest.getPrice());
		product.setStock(productRequest.getStock());
		
		return product;
		
	}
	
	public ProductResponse convertToResponse (Product product) {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setId(product.getId());
		productResponse.setCategory(product.getCategory());
		productResponse.setPartName(product.getPartName());
		productResponse.setPartNumber(product.getPartNumber());
		productResponse.setPrice(product.getPrice());
		productResponse.setStock(product.getStock());
		
		return productResponse;
	}

}
