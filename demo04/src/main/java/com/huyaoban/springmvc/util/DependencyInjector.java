package com.huyaoban.springmvc.util;

import com.huyaoban.springmvc.action.PDFAction;
import com.huyaoban.springmvc.service.PDFService;

public class DependencyInjector {
	public void start() {

	}

	public void shutdown() {

	}

	public Object getObject(Class type) {
		if (type == PDFService.class) {
			return new PDFService();
		} else if (type == PDFAction.class) {
			PDFService pdfService = (PDFService) getObject(PDFService.class);
			PDFAction action = new PDFAction();
			action.setPDFService(pdfService);
			return action;
		}

		return null;
	}
}
