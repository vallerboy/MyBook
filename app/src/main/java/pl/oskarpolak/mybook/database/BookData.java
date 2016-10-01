package pl.oskarpolak.mybook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.LoaderTestCase;
import android.util.Log;

import java.util.HashMap;

import pl.oskarpolak.mybook.Book;
import pl.oskarpolak.mybook.Utils;

/**
 * Created by OskarPraca on 2016-10-01.
 */

public class BookData {

    private Database database;
    private Context context;

    public BookData(Context context) {
        database = new Database(context);
        this.context = context;
    }

    public boolean isBookExist(String name) {
        SQLiteDatabase db = database.getReadableDatabase();
        String[] columns = {"name"};
        Cursor findEntry = db.query("book", columns, "name=?", new String[]{name}, null, null, null);
        return (findEntry.getCount() == 0) ? false : true;
    }



    public boolean addBook(String name, String author, String description, String picURL, int pages) {
        if (!isBookExist(name)) {
            SQLiteDatabase db = database.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("author", author);
            values.put("descryption", description);
            values.put("picture", picURL);
            values.put("pages", pages);
            values.put("isRented", "false");
            db.insertOrThrow("book", null, values);
            return true;
        } else {
            Utils.showMessageWithoutAction(context, "Taka książka już istnieje w bazie", "Nowa książka");
        }
        return false;
    }

    public void rentBook(String name){
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("isRented", "true");
        db.update("book", contentValues, "name=?", new String[] {name} );

    }


    public void bringBackBook(String name) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("isRented", "false");
        db.update("book", contentValues, "name=?", new String[] {name} );
    }

    public Book getBookByName(String name){
        Log.e("name:", name);
        Log.e("name:", name);
        Log.e("name:", name);
        Log.e("name:", name);

       if(!name.equals("") || !name.equals(null)) {
           SQLiteDatabase db = database.getReadableDatabase();
           String[] columns = {"name", "descryption", "picture", "pages", "author"};
           Cursor findEntry = db.query("book", columns, "name=?", new String[]{name}, null, null, null);
           findEntry.moveToFirst();
           return new Book(findEntry.getString(0), findEntry.getString(4), findEntry.getString(1), findEntry.getInt(3), findEntry.getString(2));
       }
        return null;
    }

    public HashMap<String, Book> getAllBooksToRent(){
        SQLiteDatabase db = database.getReadableDatabase();
        HashMap<String, Book> bookList = new HashMap<>();

        String[] columns = {"name, descryption, picture, pages, author"};
        Cursor findEntry = db.query("book", columns, "isRented=?", new String[]{"false"}, null, null, null);
        Log.e("baza", "zawraca : " + findEntry.getCount() );

        while (findEntry.moveToNext()){
           bookList.put(findEntry.getString(0), new Book(findEntry.getString(0), findEntry.getString(4), findEntry.getString(1), findEntry.getInt(3), findEntry.getString(2)));
            Log.e("baza", "dodaje ksiazke");
        }
        return bookList;
    }
}
