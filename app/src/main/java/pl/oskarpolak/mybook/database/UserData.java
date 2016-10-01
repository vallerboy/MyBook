package pl.oskarpolak.mybook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import pl.oskarpolak.mybook.Utils;

/**
 * Created by OskarPraca on 2016-10-01.
 */

public class UserData {

     private Database database;
     private Context context;

     public UserData(Context context){
         database = new Database(context);
         this.context = context;
     }

     public boolean isUserExist(String name){
         SQLiteDatabase db = database.getReadableDatabase();
         String[] columns =  {"login"};
         Cursor findEntry = db.query("user", columns, "login=?", new String[] { name }, null, null, null);
         return (findEntry.getCount() == 0) ? false : true;
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



    public boolean registerUser(String login, String password, String nr){
        if(!isUserExist(login)){
              SQLiteDatabase db = database.getWritableDatabase();
              ContentValues values = new ContentValues();
              values.put("login", login);
              values.put("password", password);
              values.put("telephone", Integer.parseInt(nr));
              db.insertOrThrow("user", null, values);
            return true;
        }else{
            Utils.showMessageWithoutAction(context, "Taki użytkownik już istnieje", "Rejestracja");
        }
        return false;
    }
}
