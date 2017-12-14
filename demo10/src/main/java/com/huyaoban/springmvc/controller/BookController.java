package com.huyaoban.springmvc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyaoban.springmvc.model.Book;
import com.huyaoban.springmvc.model.Category;
import com.huyaoban.springmvc.service.BookService;

@Controller
public class BookController {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/input-book")
	public String inputBook(Model model) {
		logger.info("inputBook called");
		List<Category> categories = bookService.getAllCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("book", new Book());

		return "BookAddForm";
	}

	@RequestMapping(value = "/save-book")
	public String saveBook(@ModelAttribute Book book) {
		Category category = bookService.getCategory(book.getCategory().getId());
		book.setCategory(category);
		bookService.save(book);

		return "redirect:/list-book";
	}

	@RequestMapping(value = "/edit-book/{id}")
	public String editBook(Model model, @PathVariable long id) {
		List<Category> categories = bookService.getAllCategories();
		model.addAttribute("categories", categories);

		Book book = bookService.get(id);
		model.addAttribute("book", book);

		return "BookEditForm";
	}

	@RequestMapping(value = "/update-book")
	public String updateBook(@ModelAttribute Book book) {
		Category category = bookService.getCategory(book.getCategory().getId());
		book.setCategory(category);
		bookService.update(book);
		return "redirect:/list-book";
	}

	@RequestMapping(value = "/list-book")
	public String listBooks(Model model) {
		logger.info("listBooks called");
		List<Book> books = bookService.getAllBooks();
		model.addAttribute("books", books);

		return "BookListForm";
	}
}
