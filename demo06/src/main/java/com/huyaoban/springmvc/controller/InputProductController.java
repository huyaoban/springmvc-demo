package com.huyaoban.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class InputProductController implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(InputProductController.class);

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("InputProductController called");
		return new ModelAndView("/WEB-INF/jsp/ProductForm.jsp");
		// 配置文件中添加了viewResolver，会自动加上前缀和后缀
		// return new ModelAndView("ProductForm");
	}

}
