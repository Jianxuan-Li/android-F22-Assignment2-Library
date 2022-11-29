package jianxuan.li.jllibrary.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
}
