package com.kishor.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kishor.springmvc.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

	public Product findByName(String name);

	
	
	
}
