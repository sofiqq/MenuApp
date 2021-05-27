package com.example.menuapp.model;

import java.util.ArrayList;

public class MenuItem implements Comparable{

    //Модель для хранения информации о каждом блюде в меню

    private String name; //название блюда
    private double cost; //цена блюда
    private String image; //картинка блюда
    private int type; //переменная для хранения типа блюда (первое, второе, салат)
    private String structure; //состав блюда

    public MenuItem(String name, double cost, String image, int type, String structure) {
        this.name = name;
        this.cost = cost;
        this.image = image;
        this.type = type;
        this.structure = structure;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public int getType() {
        return type;
    }

    public String getImage() {
        return image;
    }

    public String getStructure() {
        return structure;
    }

    @Override
    public int compareTo(Object o) {
        MenuItem item = (MenuItem) o;
        if (type > item.getType())
            return 1;
        else if (type == item.getType()) {
            if (cost < item.getCost())
                return 1;
        }
        return 0;
    }
}
