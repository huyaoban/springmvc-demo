package com.huyaoban.springmvc.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.huyaoban.springmvc.action.PDFAction;
import com.huyaoban.springmvc.util.DependencyInjector;

//@WebServlet(name = "ControllerServlet", urlPatterns = { "/input-product", "/save-product" })
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 2033370453720593047L;
	private DependencyInjector dependencyInjector;

	@Override
	public void init() {
		dependencyInjector = new DependencyInjector();
		dependencyInjector.start();
	}

	@Override
	public void destroy() {
		dependencyInjector.shutdown();
	}

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

		if ("form".equals(action)) {
			// no action class, just forward
			String dispatchUrl = "/jsp/Form.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
			rd.forward(request, response);
		} else if ("pdf".equals(action)) {
			HttpSession session = request.getSession(true);
			String sessionId = session.getId();
			String text = request.getParameter("text");
			String path = request.getServletContext().getRealPath("/result/") + File.separator + sessionId + ".pdf";

			PDFAction pdfAction = (PDFAction) dependencyInjector.getObject(PDFAction.class);
			pdfAction.createPDF(path, text);

			// redirect to the new pdf file
			StringBuilder redirect = new StringBuilder();
			redirect.append("http://localhost:8080");

			String contextPath = request.getContextPath();
			if (!"/".equals(contextPath)) {
				redirect.append(contextPath);
			}

			redirect.append("/result/" + sessionId + ".pdf");
			response.sendRedirect(redirect.toString());
		}
	}

}
