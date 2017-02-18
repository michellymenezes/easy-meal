package com.projeto_les.easymeal.services.retrofit_models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by caynan on 14/02/17.
 */

public class Recipe {

    @SerializedName("id")
    private Integer id;

    @SerializedName("image")
    private String image;

    @SerializedName("usedIngredientCount")
    private Integer usedIngredientCount;

    @SerializedName("missedIngredientCount")
    private Integer missedIngredientCount;

    @SerializedName("likes")
    private Integer likes;

    public Recipe() {}

    public Recipe(Integer id, String image, Integer usedIngredientCount,
                  Integer missedIngredientCount, Integer likes) {

        this.id = id;
        this.image = image;
        this.usedIngredientCount = usedIngredientCount;
        this.missedIngredientCount = missedIngredientCount;
        this.likes = likes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getUsedIngredientCount() {
        return usedIngredientCount;
    }

    public void setUsedIngredientCount(Integer usedIngredientCount) {
        this.usedIngredientCount = usedIngredientCount;
    }

    public Integer getMissedIngredientCount() {
        return missedIngredientCount;
    }

    public void setMissedIngredientCount(Integer missedIngredientCount) {
        this.missedIngredientCount = missedIngredientCount;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", usedIngredientCount=" + usedIngredientCount +
                ", missedIngredientCount=" + missedIngredientCount +
                ", likes=" + likes +
                '}';
    }


}
