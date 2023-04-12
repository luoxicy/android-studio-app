package com.st.mhappcyuan;

import java.io.Serializable;

public class News implements Serializable {
    private String id;
    private String image;
    private String title;
    private String date;
    private String author;
    private String view;
    private String content;


    public News(String id, String image, String title, String date, String author, String view, String content) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.date = date;
        this.author = author;
        this.view = view;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
