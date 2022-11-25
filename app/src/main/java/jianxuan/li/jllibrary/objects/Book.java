package jianxuan.li.jllibrary.objects;

public class Book {

    /*
    "bookId INTEGER PRIMARY KEY AUTOINCREMENT",
            "isbn TEXT",
            "bookTitle TEXT",
            "publisher TEXT",
            "qtyStock INTEGER",
            "price REAL"};
     */

    public String bookName;
    public String publisher;
    public String isbn;
    public int qtyStock;
    public double price;

    public Book(String bookName, String publisher, String isbn, int qtyStock, double price) {
        this.bookName = bookName;
        this.publisher = publisher;
        this.isbn = isbn;
        this.qtyStock = qtyStock;
        this.price = price;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

}
