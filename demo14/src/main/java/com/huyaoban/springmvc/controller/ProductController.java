package com.huyaoban.springmvc.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyaoban.springmvc.model.Product;

@Controller
public class ProductController {
	private static final Logger logger = LoggerFactory
			.getLogger(ProductController.class);

	@RequestMapping(value = "/add-product")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		return "ProductForm";
	}

	@RequestMapping(value = "/save-product")
	public String saveProduct(@Valid @ModelAttribute Product product,
			BindingResult bindResult, Model model) {
		if (bindResult.hasErrors()) {
			FieldError fieldError = bindResult.getFieldError();
			logger.debug("Code:" + fieldError.getCode() + ", field:"
					+ fieldError.getField());
			return "ProductForm";
		}

		// save product here
		model.addAttribute("product", product);
		return "ProductDetails";
	}
}
