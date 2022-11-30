package jianxuan.li.jllibrary.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import jianxuan.li.jllibrary.DBHelper;

public class StockModel {

    private Context context = null;

    public StockModel(Context cont) {
        this.context = cont;
    }

    public boolean addBook(Book book){
        DBHelper dbhelper = new DBHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("isbn", book.getIsbn());
        values.put("bookTitle", book.getBookTitle());
        values.put("publisher", book.getPublisher());
        values.put("qtyStock", book.getQtyStock());
        values.put("price", book.getPrice());
        long result = db.insert("Stock", null, values);
        db.close();

        return result != -1;
    }

    public List<Book> getBooks(){
        DBHelper dbhelper = new DBHelper(context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM Stock";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor == null){
            return books;
        }

        if (cursor.moveToFirst()){
            do {
                Book book = new Book();
                book.setId(cursor.getInt(0));
                book.setIsbn(cursor.getString(1));
                book.setBookTitle(cursor.getString(2));
                book.setPublisher(cursor.getString(3));
                book.setQtyStock(cursor.getInt(4));
                book.setPrice(cursor.getDouble(5));
                books.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return books;
    }

    public Book getBook(int bookId){
        DBHelper dbhelper = new DBHelper(context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Book book = new Book();

        String bookIdStr = String.valueOf(bookId);

        String query = "SELECT * FROM Stock WHERE bookId = " + bookIdStr;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor == null){
            return null;
        }

        if (cursor.moveToFirst()){
            book.setId(cursor.getInt(0));
            book.setIsbn(cursor.getString(1));
            book.setBookTitle(cursor.getString(2));
            book.setPublisher(cursor.getString(3));
            book.setQtyStock(cursor.getInt(4));
            book.setPrice(cursor.getDouble(5));
        }else{
            return null;
        }
        cursor.close();
        db.close();
        return book;
    }
}
