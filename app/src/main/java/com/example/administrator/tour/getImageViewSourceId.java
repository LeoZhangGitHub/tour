package com.example.administrator.tour;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2018/4/14/014.
 */

public class getImageViewSourceId {

    public static getImageViewSourceId instance;

    public static getImageViewSourceId getInstance(){

        if(instance == null){

            instance = new getImageViewSourceId();

        }

        return instance;

    }

    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
