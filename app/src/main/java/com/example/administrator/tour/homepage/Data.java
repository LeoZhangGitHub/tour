package com.example.administrator.tour.homepage;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.administrator.tour.homepage.reserveAcitivity.DataType;

/**
 * Created by Administrator on 2018/4/3/003.
 */

public class Data {
    private String title;
    private String price;
    private String content;
    private String numOfPeople;
    private String img_name;
    private DataType dataType;
    private Object imageView;

    public Object getImageView() {
        return imageView;
    }

    public void setImageView(Object imageView) {
        this.imageView = imageView;
    }

    public String getImg_name() {
        return img_name;
    }

    public void setImg_name(String img_name) {
        this.img_name = img_name;
    }

    public Data(String title, String price, String content) {
        this.title = title;
        this.price = price;
        this.content = content;
        this.imageView = imageView;
    }

    public Data(String title, String price, String content, String numOfPeople) {
        this.title = title;
        this.price = price;
        this.content = content;
        this.numOfPeople = numOfPeople;
    }

    public String getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(String numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public Data(String title, String price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
