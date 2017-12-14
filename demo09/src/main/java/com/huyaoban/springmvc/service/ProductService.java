package com.huyaoban.springmvc.service;

import com.huyaoban.springmvc.model.Product;

public interface ProductService {
	public Product add(Product product);

	public Product get(Integer id);
}
