package pl.oskarpolak.mybook.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by OskarPraca on 2016-10-01.
 */

public class UserData {

     private Database database;

     public UserData(Context context){
         database = new Database(context);
     }

     public boolean isUserExist(String name){
         SQLiteDatabase db = database.getReadableDatabase();
         String[] columns =  {"login"};
         Cursor findEntry = db.query("user", columns, "login=?", new String[] { name }, null, null, null);
         return !findEntry.isNull(0);
     }

    public boolean checkLoginAndPassword(String login, String password){
        SQLiteDatabase db = database.getReadableDatabase();
        String[] columns =  {"login, password"};
        Cursor findEntry = db.query("user", columns, "login=?", new String[] {login}, null, null, null);
        if(findEntry.getCount() == 1){
           String passwordFromDatabase =  findEntry.getString(1);
             if(password.equals(passwordFromDatabase)){
                 return true;
             }
        }
        return false;
    }
}
