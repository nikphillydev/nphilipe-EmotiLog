package com.example.nphilipe_emotilog.models;

import android.graphics.drawable.Drawable;

/**
 * This class models an emoticon with a name and an image.
 */
public class Emoticon {
    final private String name;
    final private Drawable image;
    public Emoticon(String name, Drawable image) {
        this.name = name;
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public Drawable getImage() {
        return image;
    }
}
