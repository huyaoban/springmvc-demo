package com.huyaoban.springmvc.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.huyaoban.springmvc.form.ProductForm;
import com.huyaoban.springmvc.model.Product;

public class SaveProductController implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(SaveProductController.class);

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("SaveProductController called");

		ProductForm productForm = new ProductForm();
		// populate action properties
		productForm.setName(request.getParameter("name"));
		productForm.setDescription(request.getParameter("description"));
		productForm.setPrice(request.getParameter("price"));

		// create model
		Product product = new Product();
		product.setName(productForm.getName());
		product.setDescription(productForm.getDescription());
		try {
			product.setPrice(new BigDecimal(productForm.getPrice()));
		} catch (NumberFormatException e) {

		}

		// save model to database

		// return new ModelAndView("/WEB-INF/jsp/ProductDetail.jsp", "product",
		// product);
		return new ModelAndView("ProductDetail", "product", product);
	}

}
