package com.projeto_les.easymeal;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RemoveButtonTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void removeButtonTest() {

        /// Change View from main to ingredients
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.select_ingredients_btn), withText("Select ingredients"),
                        withParent(withId(R.id.content_layout)),
                        isDisplayed()));
        appCompatButton.perform(click());

        /// Write a random ingredient's name on the container
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.auto_complete_ingredient),
                        withParent(allOf(withId(R.id.auto_complete_ingredient_container),
                                withParent(withId(R.id.ingredient_layout)))),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Lemon"), closeSoftKeyboard());

        /// Add the ingredient
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.add), withText("Add"),
                        withParent(withId(R.id.ingredient_layout)),
                        isDisplayed()));
        appCompatButton2.perform(click());

        /// Write a random ingredient's name on the container
        ViewInteraction appCompatEditText1 = onView(
                allOf(withId(R.id.auto_complete_ingredient),
                        withParent(allOf(withId(R.id.auto_complete_ingredient_container),
                                withParent(withId(R.id.ingredient_layout)))),
                        isDisplayed()));
        appCompatEditText1.perform(replaceText("Pear"), closeSoftKeyboard());

        /// Add the ingredient
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.add), withText("Add"),
                        withParent(withId(R.id.ingredient_layout)),
                        isDisplayed()));
        appCompatButton3.perform(click());

        /// Write a random ingredient's name on the container
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.auto_complete_ingredient),
                        withParent(allOf(withId(R.id.auto_complete_ingredient_container),
                                withParent(withId(R.id.ingredient_layout)))),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("Papaya"), closeSoftKeyboard());

        /// Add the ingredient
        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.add), withText("Add"),
                        withParent(withId(R.id.ingredient_layout)),
                        isDisplayed()));
        appCompatButton4.perform(click());


        /// Check if the remove button exists
        ViewInteraction button = onView(
                allOf(withId(R.id.remove_ingredient),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.selected_ingredients_list),
                                        0),
                                1),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

    /// Remove the ingredient
        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.remove_ingredient), withText("Remove"),
                        withParent(childAtPosition(
                                withId(R.id.selected_ingredients_list),
                                0))));
        appCompatButton5.perform(scrollTo(), click());

        /// Check if the remove button exists
        ViewInteraction button1 = onView(
                allOf(withId(R.id.remove_ingredient),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.selected_ingredients_list),
                                        1),
                                1),
                        isDisplayed()));
        button1.check(matches(isDisplayed()));

    /// Remove the ingredient
        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.remove_ingredient), withText("Remove"),
                        withParent(childAtPosition(
                                withId(R.id.selected_ingredients_list),
                                1))));
        appCompatButton6.perform(scrollTo(), click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
