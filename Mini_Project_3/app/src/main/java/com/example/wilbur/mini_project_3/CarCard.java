package com.example.wilbur.mini_project_3;

/**
 * Created by Wilbur on 3/4/2016.
 */
public class CarCard {
    public String model;
    public String description;
    public int imageId;
    public boolean isUsed;
    public CarCard(String model, String description, boolean isUsed, int imageId) {
        this.model = model;
        this.description = description;
        this.isUsed = isUsed;
        this.imageId = imageId;
    }
}
