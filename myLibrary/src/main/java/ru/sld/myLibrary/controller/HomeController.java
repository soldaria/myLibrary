package ru.sld.myLibrary.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ru.sld.myLibrary.enums.BookStatus;
import ru.sld.myLibrary.model.Book;
import ru.sld.myLibrary.repository.BookRepository;

@Controller
public class HomeController {
	
	@Autowired
	private BookRepository bookRepository;
	
	private static final Logger log = Logger.getLogger(HomeController.class);
		
	@GetMapping(value =  {"/","/home"})
	public String all(Map<String, Object> model) {		
		Iterable<Book> books = bookRepository.findAll();
		model.put("books", books);
		model.put("book", new Book());
		return "home";
	}
	
	@PostMapping(value = "/upload/{id}")
	public String upload(	@RequestParam("image") MultipartFile image,
							@PathVariable Long id) {	
		
		if (!image.isEmpty()) {
			try {	
				byte[] bytes = image.getBytes();
				Book existingBook = bookRepository.getOne(id);
				existingBook.setImage(bytes);
				bookRepository.saveAndFlush(existingBook);		
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return "redirect:/home";
	}
	
	@PostMapping(value = "/add_book")
	public String add(	@ModelAttribute("book") Book book){		
		book.setStatus(BookStatus.FUTURE);
		bookRepository.saveAndFlush(book);	
		log.debug("Добавлена книга " + book.getName());
		return "redirect:/home";
	}
	
	@GetMapping(value = "edit_book/{id}")
	public String edit(	@PathVariable Long id,
						Map<String, Object> model) {
		Book existingBook = bookRepository.getOne(id);
		Book book = new Book();
		BeanUtils.copyProperties(existingBook,book);
		model.put("book", book);
		return "edit_page";
	}		

	@PostMapping(value = "/update_book")
	public String update(@ModelAttribute("book") Book book) {
		bookRepository.saveAndFlush(book);
		log.debug("Отредактирована книга " + book.getName());
		return "redirect:/home";
	}

	@RequestMapping(value = "delete_book/{id}")
	public String delete(@PathVariable Long id) {
		Book existingBook = bookRepository.getOne(id);
		log.debug("Удаление книги " + existingBook.getName());
		bookRepository.delete(existingBook);
		log.debug("Книга удалена");
		return "redirect:/home";
	}
	
	@GetMapping(value = "/filter")
	public String filter(@RequestParam String filter, Map<String, Object> model) {	
		List<Book> books = new ArrayList();
		if(!filter.isEmpty())
			books = bookRepository.findByNameStartingWith(filter);
		if(books.size() == 0)
			books = bookRepository.findByAuthorStartingWith(filter);
		if(books.size() == 0)
			books = bookRepository.findByGenreStartingWith(filter);
		if(books.size() == 0)
			books = bookRepository.findAll();
		model.put("books", books);
		model.put("book", new Book());
		return "home";		
	}


}

