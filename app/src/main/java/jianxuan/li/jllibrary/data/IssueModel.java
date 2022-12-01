package jianxuan.li.jllibrary.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import jianxuan.li.jllibrary.DBHelper;

public class IssueModel {
    private Context context = null;

    public IssueModel(Context cont) {
        this.context = cont;
    }

    public boolean addIssue(Issue issue){
        DBHelper dbhelper = new DBHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("bookId", issue.getBookId());
        values.put("customerName", issue.getCustomerName());
        values.put("customerEmail", issue.getCustomerEmail());
        values.put("qtyIssued", issue.getQtyIssued());
        values.put("dateofIssue", issue.getDateOfIssue());
        long result = db.insert("Issue", null, values);
        db.close();

        return result != -1;
    }
}
