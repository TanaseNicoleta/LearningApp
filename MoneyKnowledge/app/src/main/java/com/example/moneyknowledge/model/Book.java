package com.example.moneyknowledge.model;

import java.io.Serializable;

public class Book implements Serializable {
    String title, author, publisher, id;
    String downloadLink;

    public Book(String title, String author, String publisher, String id, String downloadLink) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.id = id;
        this.downloadLink = downloadLink;
    }

    public Book() {
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
