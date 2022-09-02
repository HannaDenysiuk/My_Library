package com.example.library;

import java.io.*;
import java.util.ArrayList;

public class Library {

    public static void addNewBook(ArrayList<Book> books, String fileName){
        try(FileOutputStream stream = new FileOutputStream(fileName)){
            try(ObjectOutputStream objW = new ObjectOutputStream(stream)){
                objW.writeObject(books);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Book> getAllBooks(String fileName){
        ArrayList<Book> books = new ArrayList<>();
        try(FileInputStream stream = new FileInputStream(fileName)){
            try(ObjectInputStream objIn = new ObjectInputStream(stream)) {
                books = (ArrayList<Book>) objIn.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
    public static ArrayList<Book> authorsBooks(String author, String fileName){
        ArrayList<Book> books = new ArrayList<>();
        for (Book book : getAllBooks(fileName)
             ) {
            if(book.getAuthor().compareToIgnoreCase(author) == 0) {
                books.add(book);
            }
        }
        return books;
    }
    public static ArrayList<Book> booksByGenre(String genre, String fileName){
        ArrayList<Book> books = new ArrayList<>();
        for (Book book : getAllBooks(fileName)
        ) {
            if(book.getGenre().compareToIgnoreCase(genre) == 0) {
                books.add(book);
            }
        }
        return books;
    }

}
