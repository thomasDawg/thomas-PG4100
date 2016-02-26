package innlevering2;

public class Book {

	private String author;
	private String title;
	private String isbn;
	private int pages;
	private int year;

	public Book() {
		this(null, null, null, 0, 0);
	}

	public Book(String author, String title, String isbn, int pages, int year) {
		this.author = author;
		this.title = title;
		this.isbn = isbn;
		this.pages = pages;
		this.year = year;
	}

	public String getAuthor() {
		return author.toLowerCase();
	}

	public String getTitle() {
		return title.toLowerCase();
	}

	public String getIsbn() {
		return isbn;
	}

	public int getpages() {
		return pages;
	}

	public int getYear() {
		return year;
	}

	public String toString() {
		return "Author: " + getAuthor() + ", " + "Title: " + getTitle() + ", " + "Isbn: " + getIsbn() + ", " + "Pages: "
				+ getpages() + ", " + "Year: " + getYear();
	}

}
