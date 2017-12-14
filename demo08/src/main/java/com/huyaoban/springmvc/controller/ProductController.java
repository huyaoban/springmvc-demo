package com.huyaoban.springmvc.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyaoban.springmvc.form.ProductForm;
import com.huyaoban.springmvc.model.Product;

@Controller
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@RequestMapping(value = "/input-product")
	public String inputProduct() {
		logger.info("inputProduct called");
		return "ProductForm";
	}

	@RequestMapping(value = "/save-product")
	public String saveProduct(ProductForm productForm, Model model) {
		logger.info("saveProduct called");

		// no need to create and instantiate a ProductForm
		// create product
		Product product = new Product();
		product.setName(productForm.getName());
		product.setDescription(productForm.getDescription());
		try {
			product.setPrice(new BigDecimal(productForm.getPrice()));
		} catch (NumberFormatException e) {

		}

		// add product
		model.addAttribute("product", product);

		return "ProductDetail";
	}

}
