package jianxuan.li.jllibrary.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import jianxuan.li.jllibrary.DBHelper;

public class PublisherModel {
    private final Context context;

    public PublisherModel(Context cont) {
        context = cont;
    }


    // fetch all publishers from database
    public ArrayList<Publisher> getAllPublishers(){
        DBHelper dbhelper = new DBHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ArrayList<Publisher> publishers = new ArrayList<Publisher>();
        String query = "SELECT * FROM Publisher";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                Publisher publisher = new Publisher();
                publisher.setPublisherId(cursor.getString(0));
                publisher.setPublisher(cursor.getString(1));
                publishers.add(publisher);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return publishers;
    }
}
