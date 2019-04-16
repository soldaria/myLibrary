package ru.sld.myLibrary.enums;

public enum BookStatus {
	
	COMPLETE("Завершена"),
    PROCESS("Читаю"),
    FUTURE("Прочитать");
	
	private String title;
	
	BookStatus() {
    }	

	BookStatus(String title) {
       this.title = title;
   }

   public String getTitle() {
       return title;
   }
}
