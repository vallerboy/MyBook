package pl.oskarpolak.mybook;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OskarPraca on 2016-10-01.
 */

public class User {

    private String name;
    private String number;
    private List<String> rentedBooks;


    public User(String name, String number, String books) {
        this.name = name;
        this.number = number;
        Log.e("books", books);
        String[] booksName = books.split(".");
        rentedBooks = new ArrayList<>();
//          for(String s : booksName) {
//
//             if(!s.equals(null) || !s.equals("")) {
//                 rentedBooks.add(s);
//                 Log.e("DEBUG", s);
//             }
//          }

        for(int i = 0; i < booksName.length; i++) {
           String s = booksName[i];
            if(!s.equals(null) || !s.equals("")) {
                rentedBooks.add(s);
                Log.e("DEBUG", s);
            }
        }

    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public List<String> getRentedBooks()
    {
        Log.e("debig","" + rentedBooks.size());
        for(String s : rentedBooks) {
            Log.e("Moje ksiazki ", "Ksiazka: " + s);
        }
        return rentedBooks;
    }

    public String ArrayAtNames() {
        String s = null;
        for (String b : rentedBooks) {
            s += b + ".";
        }
        return s;
    }
}
