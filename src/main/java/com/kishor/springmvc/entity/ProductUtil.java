package com.kishor.springmvc.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;





public class ProductUtil {
	
	private static final Logger LOG = LogManager.getLogger(ProductUtil.class);

	public static List<Product> getProductList(String baseUrl, String key) {
		
		LOG.info("Inside ApiUtil#getProductList method baseUrl {}, key {}", baseUrl, key);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "viewproducts");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", key);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		List<Product> Products = new ArrayList<Product>();
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,
					entity, String.class);
			System.out.println(""+response.getBody());
			//JSONObject jsonObject = new JSONObject(response.getBody());
			
			JSONArray jsonArray = new JSONArray(response.getBody());
			
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
			
					if (object.has("id") && object.has("name") && object.has("price") && object.has("quantity")) {
						Product p = new Product();
						p.setId(object.getInt("id"));
						p.setName(object.getString("name"));
						p.setPrice(object.getInt("price"));
						p.setQuantity(object.getInt("quantity"));
						Products.add(p);
					}
			}
				
			}
		 catch(Exception e) {
			LOG.info("Exception while fetching Product list");
			System.out.println(e.getMessage());
		}
		System.out.println("size "+Products.size());
		return Products;
		}     
	
	}


