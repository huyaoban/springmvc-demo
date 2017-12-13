package com.huyaoban.springmvc.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huyaoban.springmvc.action.SaveProductAction;
import com.huyaoban.springmvc.form.ProductForm;
import com.huyaoban.springmvc.model.Product;

//@WebServlet(name = "ControllerServlet", urlPatterns = { "/input-product", "/save-product" })
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 2033370453720593047L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex + 1);

		String dispatchUrl = null;
		if ("input-product".equals(action)) {
			// no action class, just forward
			dispatchUrl = "/jsp/ProductForm.jsp";
		} else if ("save-product".equals(action)) {
			// create form
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

			// execute action method
			SaveProductAction saveProductAction = new SaveProductAction();
			saveProductAction.save(product);

			// store model in a scope variable for the view
			request.setAttribute("product", product);
			dispatchUrl = "/jsp/ProductDetail.jsp";
		}

		if (dispatchUrl != null) {
			RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
			rd.forward(request, response);
		}
	}

}
