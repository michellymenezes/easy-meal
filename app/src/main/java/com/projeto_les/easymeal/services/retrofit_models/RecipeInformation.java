package com.projeto_les.easymeal.services.retrofit_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by caynan on 17/02/17.
 */
public class RecipeInformation {

    @SerializedName("vegetarian")
    private boolean vegetarian = false;

    @SerializedName("vegan")
    private boolean vegan = false;

    @SerializedName("glutenFree")
    private boolean glutenFree = false;

    @SerializedName("dairyFree")
    private boolean dairyFree = false;

    @SerializedName("veryHealthy")
    private boolean veryHealthy = false;

    @SerializedName("cheap")
    private boolean cheap = false;

    @SerializedName("veryPopular")
    private boolean veryPopular = false;

    @SerializedName("sustainable")
    private boolean sustainable = false;

    @SerializedName("weightWatcherSmartPoints")
    private int weightWatcherSmartPoints = 0;

    @SerializedName("gaps")
    private String gaps = "";

    @SerializedName("lowFodmap")
    private boolean lowFodmap = false;

    @SerializedName("extendedIngredients")
    private List<ExtendedIngredient> extendedIngredients;
//
//
//            "gaps": "no",
//            "lowFodmap": false,
//            "ketogenic": false,
//            "whole30": false,
//            "servings": 10,
//            "sourceUrl": "http://www.epicurious.com/recipes/food/views/Char-Grilled-Beef-Tenderloin-with-Three-Herb-Chimichurri-235342",
//            "spoonacularSourceUrl": "https://spoonacular.com/char-grilled-beef-tenderloin-with-three-herb-chimichurri-156992",
//            "aggregateLikes": 0,
//            "creditText": "Epicurious",
//            "sourceName": "Epicurious",
//            "extendedIngredients": [
//                {
//                    "id": 1022009,
//                        "aisle": "Ethnic Foods",
//                        "image": "https://spoonacular.com/cdn/ingredients_100x100/chili-powder.jpg",
//                        "name": "ancho chile powder",
//                        "amount": 1.5,
//                        "unit": "teaspoons",
//                        "unitShort": "t",
//                        "unitLong": "teaspoons",
//                        "originalString": "1 1/2 teaspoons chipotle chile powder or ancho chile powder",
//                        "metaInformation": []
//                },
//                {...}
//            ],
//            "id": 156992,
//            "title": "Char-Grilled Beef Tenderloin with Three-Herb Chimichurri",
//            "readyInMinutes": 45,
//            "image": "https://spoonacular.com/recipeImages/char-grilled-beef-tenderloin-with-three-herb-chimichurri-156992.jpg",
//            "imageType": "jpg",
//            "instructions": "PreparationFor spice rub:                                        Combine all ingredients in small bowl.                                                                            Do ahead: Can be made 2 days ahead. Store airtight at room temperature.                                    For chimichurri sauce:                                        Combine first 8 ingredients in blender; blend until almost smooth. Add 1/4 of parsley, 1/4 of cilantro, and 1/4 of mint; blend until incorporated. Add remaining herbs in 3 more additions, pureeing until almost smooth after each addition.                                                                            Do ahead: Can be made 3 hours ahead. Cover; chill.                                    For beef tenderloin:                                        Let beef stand at room temperature 1 hour.                                                                            Prepare barbecue (high heat). Pat beef dry with paper towels; brush with oil. Sprinkle all over with spice rub, using all of mixture (coating will be thick). Place beef on grill; sear 2 minutes on each side. Reduce heat to medium-high. Grill uncovered until instant-read thermometer inserted into thickest part of beef registers 130F for medium-rare, moving beef to cooler part of grill as needed to prevent burning, and turning occasionally, about 40 minutes. Transfer to platter; cover loosely with foil and let rest 15 minutes. Thinly slice beef crosswise. Serve with chimichurri sauce.                                                                            *Available at specialty foods stores and from tienda.com."

}
