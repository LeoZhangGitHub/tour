package com.example.administrator.tour;

/**
 * Created by Administrator on 2018/4/1/001.
 */

public class BrowseData {

    private String title;

    private String content;

    private String imageView;

    public String getImageView() {
        return imageView;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }

    public BrowseData(String title, String content, String imageView) {
        this.title = title;
        this.content = content;
        this.imageView = imageView;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
