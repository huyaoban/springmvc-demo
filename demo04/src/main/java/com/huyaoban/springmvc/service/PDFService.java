package com.huyaoban.springmvc.service;

import com.huyaoban.springmvc.util.PDFUtil;

public class PDFService {
	public void createPDF(String path, String input) {
		PDFUtil.createDocument(path, input);
	}
}
