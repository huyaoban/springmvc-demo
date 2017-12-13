package com.huyaoban.springmvc.util;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFUtil {
	public static void createDocument(String path, String input) {
		try {
			PDDocument document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage(page);

			PDFont font = PDType1Font.HELVETICA_BOLD;
			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			contentStream.beginText();
			contentStream.setFont(font, 14);
			contentStream.moveTextPositionByAmount(100, 700);
			contentStream.drawString(input);
			contentStream.endText();
			contentStream.close();

			document.save(path);
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getCause());
		}
	}
}
