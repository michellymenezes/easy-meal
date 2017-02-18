package com.projeto_les.easymeal.services.retrofit_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by caynan on 17/02/17.
 */
public class ExtendedIngredient {

    @SerializedName("metaInformation")
    private List<String> metaInformation;

    //TODO: Never forget to include the default constructor when you overwrite it. Retrofit needs it.
    public ExtendedIngredient() {}


    public ExtendedIngredient(List<String> metaInformation) {
        this.metaInformation = metaInformation;
    }

    public List<String> getMetaInformation() {
        return this.metaInformation;
    }

    public void setMetaInformation(List<String> metaInformation) {
        this.metaInformation = metaInformation;
    }

    @Override
    public String toString() {
        return "ExtendedIngredient{" +
                "metaInformation=" + metaInformation +
                '}';
    }
}
