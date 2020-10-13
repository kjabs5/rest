package com.kishor.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.kishor.springmvc.entity.Product;
import com.kishor.springmvc.entity.ProductInfo;
import com.kishor.springmvc.entity.ProductUtil;
import com.kishor.springmvc.service.ProductService;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/api")

public class ProductController {
	@Value("${url}")
	private String baseUrl;

	@Value("${api-key}")
	private String key;
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/add-product")
	//public Product addProduct(@RequestBody Product product)
	public ResponseEntity<Void> addProduct(@RequestBody Product product)
	{
		//return productService.saveProduct(product);
		productService.saveProduct(product);
		HttpHeaders header=new HttpHeaders();
		header.add("desc","Adding a product");
		//return ResponseEntity.ok().build();
		return ResponseEntity.status(HttpStatus.OK).headers(header).build();
		
	}
	
	@PostMapping("/add-products")
	//public List<Product> addProducts(@RequestBody List<Product> products)
	public ResponseEntity<Void> addProducts(@RequestBody List<Product> products)
	{
		//return productService.saveProductList(products);
		productService.saveProductList(products);
		HttpHeaders header=new HttpHeaders();
		header.add("desc","Adding different products");
		//return ResponseEntity.ok().build();
		return ResponseEntity.status(HttpStatus.OK).headers(header).build();
	}
	

	@GetMapping("/product-list")
	//public List<Product> findAllProducts()
	public ResponseEntity<ProductInfo> findAllProducts()
	{
		//return productService.getAllProducts();
		List<Product> products=productService.getAllProducts();
		ProductInfo pinfo=new ProductInfo();
		pinfo.setProducts(products);
		HttpHeaders header=new HttpHeaders();
		header.add("desc","Getting product List");
		header.add("type", "product object");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(pinfo);
	}
	
	@GetMapping("/product-by-id/{id}")
	//public Product getProductById(@PathVariable Integer id)
	public ResponseEntity<Product> getProductById(@PathVariable Integer id)
	{   
		Product product=productService.getProductById(id);
		//return productService.getProductById(id);
		HttpHeaders header=new HttpHeaders();
		header.add("desc","Getting product by id");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(product);
	}
	
	@GetMapping("/product/{name}")
	//public Product getProductByName(@PathVariable String name)
	public ResponseEntity<Product> getProductByName(@PathVariable String name)
	{
		//return productService.getProductByName(name);
		Product product=productService.getProductByName(name);
		HttpHeaders header=new HttpHeaders();
		header.add("desc","Getting product by name");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(product);
		
	}
	
	@DeleteMapping("/delete/{id}")
	//public String deleteProduct(@PathVariable Integer id)
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id)
	{
		//return productService.deleteProduct(id);
		String msg=productService.deleteProduct(id);
		HttpHeaders header=new HttpHeaders();
		header.add("desc","Deleted the product");
		return new ResponseEntity<String>(msg,header,HttpStatus.OK);
		
		
	}
	
	@PutMapping("/update-product")
	//public Product updateProduct(Product product)
	public ResponseEntity<Product> updateProduct(Product product)
	{
		//return productService.updateProduct(product);
		Product prod=productService.updateProduct(product);
		HttpHeaders header=new HttpHeaders();
		header.add("desc","updating product");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(prod);
		
	}
	
	@GetMapping("/view-api-product")
	//public Product updateProduct(Product product)
	public ResponseEntity<List<Product>> viewProduct(Product product)
	{
		List<Product> products =ProductUtil.getProductList(baseUrl, key);
		HttpHeaders header=new HttpHeaders();
		
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(products);
		
	}
	

}
