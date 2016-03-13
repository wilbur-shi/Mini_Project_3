package com.example.wilbur.mini_project_4;

/**
 * Created by Wilbur on 3/10/2016.
 */
public class Location {
    public String name;
    public String description;
    public byte[] imageByteArray;

    public Location(String name, String description, byte[] imageByteArray) {
        this.name = name;
        this.description = description;
        this.imageByteArray = imageByteArray;
    }
}
