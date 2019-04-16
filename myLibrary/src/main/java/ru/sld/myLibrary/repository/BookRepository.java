package ru.sld.myLibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.sld.myLibrary.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	List<Book> findByNameStartingWith(String name);
	
	List<Book> findByAuthorStartingWith(String author);
	
	List<Book> findByGenreStartingWith(String genre);
	
}
