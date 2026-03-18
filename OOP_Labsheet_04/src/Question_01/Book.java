package Question_01;

public class Book {
    private String title;
    private String author;
    private String ISBN;
    double price;

    // Constructor 1
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
    // Constructor 2
    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
    }

    // Constructor 3
    public Book(String title, String author, String ISBN, double price) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.price = price;
    }

    // Method Overloading
    public String getBookDetails() {
        return "Title: " + title + " Author: " + author +
                " ISBN: " + ISBN + " Price: " + price;
    }

    public String getBookDetails(String ISBN) {
        if(this.ISBN != null && this.ISBN.equals(ISBN)) {
            return getBookDetails();
        }
        return "Book not found";
    }

}
