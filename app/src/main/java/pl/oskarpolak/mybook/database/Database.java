package pl.oskarpolak.mybook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by OskarPraca on 2016-10-01.
 */

public class Database extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context, "MyBook.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
          db.execSQL("create table user(id integer primary key autoincrement, login text, password text, telephone integer, rentedBooks text);");
          db.execSQL("create table book(id integer primary key autoincrement, picture text, name text, author text, descryption text, pages integer, isRented boolean);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
