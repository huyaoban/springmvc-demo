package com.huyaoban.springmvc.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.huyaoban.springmvc.model.Product;
import com.huyaoban.springmvc.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private Map<Integer, Product> products = new HashMap<Integer, Product>();
	private AtomicInteger generator = new AtomicInteger();

	public ProductServiceImpl() {
		Product product = new Product();
		product.setName("JX1 Power Drill");
		product.setDescription("Powerful hand drill, made to perfection");
		product.setPrice(new BigDecimal(129.99));

		add(product);
	}

	@Override
	public Product add(Product product) {
		Integer newId = generator.incrementAndGet();
		product.setId(newId);
		products.put(newId, product);
		return product;
	}

	@Override
	public Product get(Integer id) {
		return products.get(id);
	}

}
