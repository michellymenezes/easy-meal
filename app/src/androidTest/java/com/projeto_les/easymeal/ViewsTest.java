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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ViewsTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void viewsTest() {
        /// Changes view from main to ingredients
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.select_ingredients_btn), withText("Select ingredients"),
                        withParent(withId(R.id.content_layout)),
                        isDisplayed()));
        appCompatButton.perform(click());

        /// Checks if back button to main exists
        ViewInteraction imageButton = onView(
                allOf(withId(R.id.back_menu_btn),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        3),
                                0),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        /// Changes view to ingredients to main
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.back_menu_btn), isDisplayed()));
        appCompatImageButton.perform(click());

        /// Checks if ingredients button exists
        ViewInteraction button = onView(
                allOf(withId(R.id.select_ingredients_btn),
                        childAtPosition(
                                allOf(withId(R.id.content_layout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        /// Tries to change view without adding ingredients
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.select_ingredients_btn), withText("Select ingredients"),
                        withParent(withId(R.id.content_layout)),
                        isDisplayed()));
        appCompatButton2.perform(click());

        /// Check if the filters button exists
        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.next),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        3),
                                1),
                        isDisplayed()));
        imageButton2.check(matches(isDisplayed()));

        /// Adds an ingredient
        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.next), isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.auto_complete_ingredient),
                        withParent(allOf(withId(R.id.auto_complete_ingredient_container),
                                withParent(withId(R.id.ingredient_layout)))),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Egg"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.add), withText("Add"),
                        withParent(withId(R.id.ingredient_layout)),
                        isDisplayed()));
        appCompatButton3.perform(click());

        /// Changes view to filters
        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.next), isDisplayed()));
        appCompatImageButton3.perform(click());

        /// Checks if button to go back to ingredients exists
        ViewInteraction imageButton3 = onView(
                allOf(withId(R.id.back),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        imageButton3.check(matches(isDisplayed()));

        /// Changes view to ingredients
        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.back), isDisplayed()));
        appCompatImageButton4.perform(click());

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
