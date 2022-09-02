package com.example.library;

import java.io.*;
import java.util.Objects;

public class Book implements Serializable {
    private String author;
    private String title;
    private String genre;
    private String publishingHouse;
    private int year;
    private int pages;

    public Book() {
    }

    public Book(String author, String title, String genre, String publishingHouse, int year, int pages) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.publishingHouse = publishingHouse;
        this.year = year;
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "author: " + author +
                " title: " + title +
                " genre: " + genre +
                " publishingHouse: " + publishingHouse +
                " year: " + year +
                " pages: " + pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(book.author, author) &&
                Objects.equals(book.title, title) &&
                Objects.equals(book.genre, genre) &&
                Objects.equals(book.publishingHouse, publishingHouse) &&
                Objects.equals(book.year, year) &&
                Objects.equals(book.pages, pages);
    }
    @Override
    public int hashCode() {
        return Objects.hash(author, title, genre, publishingHouse, year, pages);
    }
}
