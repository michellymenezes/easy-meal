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
public class AddButtonTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void AddButtonTest() {
        /// Tests the view change from main view to ingredients view
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.select_ingredients_btn), withText("Select ingredients"),
                        withParent(withId(R.id.content_layout)),
                        isDisplayed()));
        appCompatButton.perform(click());

        /// Writes an ingredient name on the ingredients container
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.auto_complete_ingredient),
                        withParent(allOf(withId(R.id.auto_complete_ingredient_container),
                                withParent(withId(R.id.ingredient_layout)))),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Tomato"), closeSoftKeyboard());

        /// Adds the ingredient
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.add), withText("Add"),
                        withParent(withId(R.id.ingredient_layout)),
                        isDisplayed()));
        appCompatButton2.perform(click());

        /// Writes another ingredient name on the ingredients container
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.auto_complete_ingredient),
                        withParent(allOf(withId(R.id.auto_complete_ingredient_container),
                                withParent(withId(R.id.ingredient_layout)))),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("Potato"), closeSoftKeyboard());

        /// Adds the ingredient
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.add), withText("Add"),
                        withParent(withId(R.id.ingredient_layout)),
                        isDisplayed()));
        appCompatButton3.perform(click());

        /// Writes another ingredient name on the ingredients container
        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.auto_complete_ingredient),
                        withParent(allOf(withId(R.id.auto_complete_ingredient_container),
                                withParent(withId(R.id.ingredient_layout)))),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("Lettuce"), closeSoftKeyboard());

        /// Adds the ingredient
        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.add), withText("Add"),
                        withParent(withId(R.id.ingredient_layout)),
                        isDisplayed()));
        appCompatButton4.perform(click());

        /// Checks if the add button exists
        ViewInteraction button = onView(
                allOf(withId(R.id.add),
                        childAtPosition(
                                allOf(withId(R.id.ingredient_layout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                2),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

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
