package com.huyaoban.springmvc.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.huyaoban.springmvc.action.SaveProductAction;
import com.huyaoban.springmvc.form.ProductForm;
import com.huyaoban.springmvc.model.Product;

//@WebFilter(filterName = "DispatchFilter", urlPatterns = { "/*" })
public class DispatcherFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		String uri = req.getRequestURI();
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

		// forward to a view
		if (dispatchUrl != null) {
			RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
			rd.forward(request, response);
		} else {
			// let static contents pass
			filterChain.doFilter(request, response);
		}
	}

}
