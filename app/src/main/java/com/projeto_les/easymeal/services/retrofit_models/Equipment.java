package com.projeto_les.easymeal.services.retrofit_models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by michelly on 25/02/17.
 */

public class Equipment {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private  String image;

    public Equipment(){}

    public Equipment(String id, String name, String image){
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
