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
    private int price;
    private String content;
    private int numOfPeople;
    private String imageView;

    public String getImageView() {
        return imageView;
    }

    //homapage site data
    public Data(String title, int price, String content,String imageView) {
        this.title = title;
        this.price = price;
        this.content = content;
        this.imageView = imageView;
    }

    //hotelActivity data
    public Data(String title,int price,String imageView){
        this.title = title;
        this.price = price;
        this.imageView = imageView;
    }

    //homepage group_ticket data
    public Data(String title, int price, String content, int numOfPeople,String imageView) {
        this.title = title;
        this.price = price;
        this.content = content;
        this.numOfPeople = numOfPeople;
        this.imageView = imageView;
    }




    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    //homepage traffic data
    public Data(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
