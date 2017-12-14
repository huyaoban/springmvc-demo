package com.huyaoban.springmvc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.huyaoban.springmvc.model.Book;
import com.huyaoban.springmvc.model.Category;
import com.huyaoban.springmvc.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	private List<Category> categories;
	private List<Book> books;

	public BookServiceImpl() {
		categories = new ArrayList<Category>();
		Category category1 = new Category(1, "Compute");
		Category category2 = new Category(2, "Travel");
		Category category3 = new Category(3, "Health");
		categories.add(category1);
		categories.add(category2);
		categories.add(category3);

		books = new ArrayList<Book>();
		Book book1 = new Book(1L, "9781771970273", "Servlet & JSP: A Tutorial (2nd Edition)", category1,
				"Budi Kurniawan", new BigDecimal(54.99));
		Book book2 = new Book(2L, "9781771970277", "C#: A Beginner's Tutorial (2nd Edition)", category1,
				"Jayden Ky", new BigDecimal(39.99));
		books.add(book1);
		books.add(book2);
	}

	@Override
	public List<Category> getAllCategories() {
		return categories;
	}

	@Override
	public Category getCategory(int id) {
		for (Category category : categories) {
			if (category.getId() == id) {
				return category;
			}
		}

		return null;
	}

	@Override
	public List<Book> getAllBooks() {
		return books;
	}

	@Override
	public Book save(Book book) {
		book.setId(getNextId());
		books.add(book);

		return book;
	}

	@Override
	public Book update(Book book) {
		int bookCount = books.size();
		for (int i = 0; i < bookCount; i++) {
			Book savedBook = books.get(i);
			if (savedBook.getId() == book.getId()) {
				books.set(i, book);
				return book;
			}
		}

		return book;
	}

	@Override
	public Book get(long id) {
		for (Book book : books) {
			if (book.getId() == id) {
				return book;
			}
		}
		return null;
	}

	@Override
	public long getNextId() {
		long id = 0L;
		for (Book book : books) {
			long bookId = book.getId();
			if (bookId > id) {
				id = bookId;
			}
		}

		return id + 1;
	}

}
