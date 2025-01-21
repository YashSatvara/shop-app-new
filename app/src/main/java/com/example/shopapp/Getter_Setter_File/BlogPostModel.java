package com.example.shopapp.Getter_Setter_File;

public class BlogPostModel {
    private String title;
    private String content;

    public BlogPostModel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
