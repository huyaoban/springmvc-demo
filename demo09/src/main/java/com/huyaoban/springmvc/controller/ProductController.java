package com.huyaoban.springmvc.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.huyaoban.springmvc.form.ProductForm;
import com.huyaoban.springmvc.model.Product;
import com.huyaoban.springmvc.service.ProductService;

@Controller
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/input-product")
	public String inputProduct() {
		logger.info("inputProduct called");
		return "ProductForm";
	}

	@RequestMapping(value = "/save-product", method = RequestMethod.POST)
	public String saveProduct(ProductForm productForm, RedirectAttributes redirectAttributes) {
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
		Product savedProduct = productService.add(product);

		redirectAttributes.addFlashAttribute("message", "The product was successfully added.");

		// 重定向到product view页面
		return "redirect:/product-view/" + savedProduct.getId();
	}

	@RequestMapping(value = "/product-view/{id}")
	public String viewProduct(@PathVariable Integer id, Model model) {
		Product product = productService.get(id);
		model.addAttribute("product", product);

		return "ProductDetail";
	}

}
