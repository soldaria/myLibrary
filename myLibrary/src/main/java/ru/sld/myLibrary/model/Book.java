package ru.sld.myLibrary.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import ru.sld.myLibrary.enums.BookStatus;

/** Класс служит для хранения объектов-книг со свойствами
<b>id</b> <b>name</b> <b>author</b> <b>genre</b> <b>sheetscnt</b>
@author soldaria
@version 1.0
*/
@Entity
public class Book {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	/** Свойство - id */
    private Long id;
	/** Свойство - название */
    private String name;
    /** Свойство - автор */
    private String author;
    /** Свойство - жанр */
    private String genre;
    /** Свойство - количество страниц */
    private Integer sheetscnt;
    @Lob    
    /** Свойство - обложка */
	private byte[] image;
    /** Свойство - текущий статус */
    @Enumerated(EnumType.STRING)
    private BookStatus status;    
    
	/** Получает значение свойства name, которое можно задать с помощью метода {@link #setName(String)}
    @return название книги
    */
	public String getName() {
		return name;
	}
	/** Задает значение свойства name, которое можно получить при помощи метода {@link #getName()}
    @param name - название книги
    */
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Integer getSheetscnt() {
		return sheetscnt;
	}
	public void setSheetscnt(Integer sheetscnt) {
		this.sheetscnt = sheetscnt;
	}
	
	public BookStatus getStatus() {
		return status;
	}
	public void setStatus(BookStatus status) {
		this.status = status;
	}

}
