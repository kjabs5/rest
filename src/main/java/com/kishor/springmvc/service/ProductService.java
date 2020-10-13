package com.kishor.springmvc.service;

import java.util.List;

import com.kishor.springmvc.entity.Product;

public interface ProductService {
	
	public Product saveProduct(Product product);
	
	public List<Product> saveProductList(List<Product> products);
	
	public List<Product> getAllProducts();
	
	public Product getProductById(Integer id);
	
	public Product getProductByName(String name);
	
	public String deleteProduct(Integer id);
	
	public Product updateProduct(Product product);
	
	

}
