package jianxuan.li.jllibrary.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import jianxuan.li.jllibrary.DBHelper;

public class ReturnModel {
    private Context context = null;

    public ReturnModel(Context cont) {
        this.context = cont;
    }

    public boolean addReturn(Return ret){
        DBHelper dbhelper = new DBHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("issueId", ret.getIssueId());
        values.put("bookId", ret.getBookId());
        values.put("qtyReturned", ret.getQtyReturned());
        values.put("dateofReturn", ret.getDateOfReturn());
        long result = db.insert("Return", null, values);
        db.close();

        return result != -1;
    }
}
