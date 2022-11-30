package jianxuan.li.jllibrary.data;

public class Book {
    /*
    data model for book:{
            "isbn TEXT",
            "bookTitle TEXT",
            "publisher TEXT",
            "qtyStock INTEGER",
            "price REAL"
            }
     */

    private int id;
    private String isbn;
    private String bookTitle;
    private String publisher;
    private int qtyStock;
    private double price;

    // getters and setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getQtyStock() {
        return qtyStock;
    }

    public void setQtyStock(int qtyStock) {
        this.qtyStock = qtyStock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // constructor
    public Book() {
    }
}
