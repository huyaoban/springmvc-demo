package com.huyaoban.springmvc.service;

import java.util.List;

import com.huyaoban.springmvc.model.Book;
import com.huyaoban.springmvc.model.Category;

public interface BookService {
	public List<Category> getAllCategories();

	public Category getCategory(int id);

	public List<Book> getAllBooks();

	public Book save(Book book);

	public Book update(Book book);

	public Book get(long id);

	public long getNextId();
}
