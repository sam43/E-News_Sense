package com.androidbelieve.drawerwithswipetabs.DrawerFragments.Models;

import android.graphics.drawable.Drawable;

public class BanglaNewspaperDataModel {
    private Drawable image;
    private String name;
    private int id;

    public BanglaNewspaperDataModel(){

    }

    public BanglaNewspaperDataModel(Drawable image, String name, int id) {
        this.image = image;
        this.name = name;
        this.id = id;
    }

    public Drawable getImge() {
        return image;
    }

    public void setImge(Drawable imge) {
        this.image = imge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
