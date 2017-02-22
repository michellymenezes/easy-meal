package com.projeto_les.easymeal;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RepeatedIngredientTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void repeatedIngredientTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.select_ingredients_btn), withText("Select ingredients"),
                        withParent(withId(R.id.content_layout)),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.auto_complete_ingredient),
                        withParent(allOf(withId(R.id.auto_complete_ingredient_container),
                                withParent(withId(R.id.ingredient_layout)))),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Ice"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.add), withText("Add"),
                        withParent(withId(R.id.ingredient_layout)),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.auto_complete_ingredient),
                        withParent(allOf(withId(R.id.auto_complete_ingredient_container),
                                withParent(withId(R.id.ingredient_layout)))),
                        isDisplayed()));
        appCompatEditText2.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.auto_complete_ingredient),
                        withParent(allOf(withId(R.id.auto_complete_ingredient_container),
                                withParent(withId(R.id.ingredient_layout)))),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("Milk"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.add), withText("Add"),
                        withParent(withId(R.id.ingredient_layout)),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.auto_complete_ingredient),
                        withParent(allOf(withId(R.id.auto_complete_ingredient_container),
                                withParent(withId(R.id.ingredient_layout)))),
                        isDisplayed()));
        appCompatEditText4.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.auto_complete_ingredient),
                        withParent(allOf(withId(R.id.auto_complete_ingredient_container),
                                withParent(withId(R.id.ingredient_layout)))),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("Ice"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.add), withText("Add"),
                        withParent(withId(R.id.ingredient_layout)),
                        isDisplayed()));
        appCompatButton4.perform(click());

    }

}
