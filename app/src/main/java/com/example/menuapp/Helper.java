package com.example.menuapp;

import android.content.Context;
import android.util.Log;

import com.example.menuapp.model.MenuItem;

import java.util.ArrayList;
import java.util.Collections;

public class Helper {

    public static ArrayList<MenuItem> getMenu(Context context) {
        ArrayList<MenuItem> menu = new ArrayList<>();
        String[] dishes = context.getResources().getStringArray(R.array.dishes);
        int[] costs = context.getResources().getIntArray(R.array.costs);
        String[] images = context.getResources().getStringArray(R.array.images);
        int[] types = context.getResources().getIntArray(R.array.types);


        for (int i = 0; i < dishes.length; i++) {
            MenuItem item = new MenuItem(dishes[i], costs[i], images[i], types[i]);
            Log.e("ASD", "type = " + types[i]);
            menu.add(item);
        }

        Collections.sort(menu);
        return menu;
    }

    public static String getTypeById(int type) {
        if (type == 1)
            return "Первые блюда";
        else if (type == 2)
            return "Вторые блюда";
        else if (type == 3)
            return "Салаты";
        else if (type == 4)
            return "Напитки";
        else return "";
    }

}
