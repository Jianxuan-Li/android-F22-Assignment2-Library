package jianxuan.li.jllibrary.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public Issue getIssue(int issueId){
        DBHelper dbhelper = new DBHelper(context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String[] cols = {"issueId", "bookId", "customerName", "customerEmail", "qtyIssued", "dateofIssue"};
        String[] args = {String.valueOf(issueId)};
        Cursor cursor = db.query("Issue", cols, "issueId=?", args, null, null, null);
        Issue issue = null;
        if(cursor.moveToNext()){
            issue = new Issue();
            issue.setIssueId(cursor.getInt(0));
            issue.setBookId(cursor.getInt(1));
            issue.setCustomerName(cursor.getString(2));
            issue.setCustomerEmail(cursor.getString(3));
            issue.setQtyIssued(cursor.getInt(4));
            issue.setDateOfIssue(cursor.getString(5));
        }
        cursor.close();
        db.close();
        return issue;
    }

    public boolean updateIssue(Issue issue){
        DBHelper dbhelper = new DBHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("bookId", issue.getBookId());
        values.put("customerName", issue.getCustomerName());
        values.put("customerEmail", issue.getCustomerEmail());
        values.put("qtyIssued", issue.getQtyIssued());
        values.put("dateofIssue", issue.getDateOfIssue());
        String[] args = {String.valueOf(issue.getIssueId())};
        int result = db.update("Issue", values, "issueId=?", args);
        db.close();
        return result > 0;
    }
}
