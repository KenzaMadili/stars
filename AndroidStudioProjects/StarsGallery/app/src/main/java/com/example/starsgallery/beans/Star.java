package com.example.starsgallery.beans;

public class Star {

    private int id;
    private String name;
    private int img;
    private float rating;

    private static int counter = 1;

    public Star(String name, int img, float rating) {
        this.id = counter++;
        this.name = name;
        this.img = img;
        this.rating = rating;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getImg() { return img; }
    public float getRating() { return rating; }

    public void setName(String name) { this.name = name; }
    public void setImg(int img) { this.img = img; }
    public void setRating(float rating) { this.rating = rating; }
}