package com.kishor.springmvc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kishor.springmvc.entity.Product;
import com.kishor.springmvc.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ProductServiceImpl.class);
    
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Product saveProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	@Override
	public List<Product> saveProductList(List<Product> products) {
		// TODO Auto-generated method stub
		return productRepository.saveAll(products);
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Integer id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public Product getProductByName(String name) {
		// TODO Auto-generated method stub
		//return productRepository.findByName(name);
		return productRepository.findAll().stream()
				                          .filter((product)->product.getName().equals(name))
				                          .findAny()
				                          .orElse(null);
	}

	@Override
	public String deleteProduct(Integer id) {
		// TODO Auto-generated method stub
		productRepository.deleteById(id);
		return "Product deleted"+id;
	}

	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		LOGGER.info(""+product.getId());
		System.out.println(""+product.getId());
		Product existingProduct=productRepository.findById(product.getId()).orElse(null);
		existingProduct.setName(product.getName());
		existingProduct.setQuantity(product.getQuantity());
		existingProduct.setPrice(product.getPrice());
		return productRepository.save(existingProduct);
	}

}
