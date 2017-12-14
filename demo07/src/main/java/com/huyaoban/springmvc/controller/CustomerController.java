package com.huyaoban.springmvc.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
// @RequestMapping(value = "/customer")
// RequestMapping也可以用来注解一个控制器类，
// 这种情况下，所有方法都将映射为相对于类级别的请求，如/customer/input-customer
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	// value属性是RequestMapping注解的默认属性，若只有一个属性，则可以省略属性名称，如下2个注解含义相同
	@RequestMapping(value = "/input-customer")
	//@RequestMapping("input-customer")
	public String inputCustomer() {
		logger.info("inputCustomer method called");
		return "CustomerForm";
	}

	// method属性用来指示该方法仅处理哪些http方法，若method属性只有一个值，则无需花括号
	// 如果没有制定method属性值，则可以处理任意http方法
	@RequestMapping(value = "/process-order", method = { RequestMethod.POST, RequestMethod.PUT })
	// @RequestMapping(value = "/process-order", method = RequestMethod.POST)
	public String processOrder() {
		return "OrderForm";
	}

	// 每个请求处理方法可以有多个不同类型的参数，以及一个多种类型的返回结果。例如，如果要在请求处理方法中需要访问HttpSession对象，
	// 则可以添加HttpSession作为参数，Spring会将对象正确地传递给方法。
	@RequestMapping(value = "/uri1")
	public String myMethod(HttpSession session) {
		session.getAttribute("test");
		return "SessionForm";
	}

	// 或者要访问客户端语言环境和HttpServletRequest对象
	@RequestMapping(value = "/uri2")
	public String myOtherMethod(HttpServletRequest request, Locale locale) {
		return "Uri2Form";
	}

	// 请求处理方法可以返回如下类型的对象
	// ModelAndView
	// Model
	// 包含模型的属性的Map
	// View
	// 代表逻辑视图的String
	// void
	// 等等
}
