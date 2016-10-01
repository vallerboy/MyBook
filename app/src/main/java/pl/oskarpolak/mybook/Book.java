package pl.oskarpolak.mybook;

import java.util.HashMap;

/**
 * Created by OskarPraca on 2016-10-01.
 */

public class Book {

    // nazwa > obiekt Book
    public static HashMap<String, Book> ALL_BOOKS = new HashMap<>();

    private String name;
    private String author;
    private String description;
    private int pages;
    private String imageURL;

    public Book(String name, String author, String description, int pages, String imageURL) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.pages = pages;
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getPages() {
        return pages;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }
}
