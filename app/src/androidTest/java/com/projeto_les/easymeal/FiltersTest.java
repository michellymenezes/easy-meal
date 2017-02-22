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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FiltersTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void filtersTest() {
        /// Changes view from main to Ingredients
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.select_ingredients_btn), withText("Select ingredients"),
                        withParent(withId(R.id.content_layout)),
                        isDisplayed()));
        appCompatButton.perform(click());

        /// Adds an ingredient
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.auto_complete_ingredient),
                        withParent(allOf(withId(R.id.auto_complete_ingredient_container),
                                withParent(withId(R.id.ingredient_layout)))),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Chocolate"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.add), withText("Add"),
                        withParent(withId(R.id.ingredient_layout)),
                        isDisplayed()));
        appCompatButton2.perform(click());

        /// Changes view to filters
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.next), isDisplayed()));
        appCompatImageButton.perform(click());

        /// Checks if all the filters exist
        ViewInteraction scrollView = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.content_layout),
                                0),
                        0),
                        isDisplayed()));
        scrollView.check(matches(isDisplayed()));

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.filter_list),
                                childAtPosition(
                                        withId(R.id.filter_layout),
                                        1)),
                        0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction linearLayout2 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.filter_list),
                                childAtPosition(
                                        withId(R.id.filter_layout),
                                        1)),
                        1),
                        isDisplayed()));
        linearLayout2.check(matches(isDisplayed()));

        ViewInteraction linearLayout3 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.filter_list),
                                childAtPosition(
                                        withId(R.id.filter_layout),
                                        1)),
                        2),
                        isDisplayed()));
        linearLayout3.check(matches(isDisplayed()));

        ViewInteraction linearLayout4 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.filter_list),
                                childAtPosition(
                                        withId(R.id.filter_layout),
                                        1)),
                        3),
                        isDisplayed()));
        linearLayout4.check(matches(isDisplayed()));

        ViewInteraction linearLayout5 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.filter_list),
                                childAtPosition(
                                        withId(R.id.filter_layout),
                                        1)),
                        4),
                        isDisplayed()));
        linearLayout5.check(matches(isDisplayed()));

        ViewInteraction linearLayout6 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.filter_list),
                                childAtPosition(
                                        withId(R.id.filter_layout),
                                        1)),
                        5),
                        isDisplayed()));
        linearLayout6.check(matches(isDisplayed()));

        ViewInteraction linearLayout7 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.filter_list),
                                childAtPosition(
                                        withId(R.id.filter_layout),
                                        1)),
                        6),
                        isDisplayed()));
        linearLayout7.check(matches(isDisplayed()));

        ViewInteraction linearLayout8 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.filter_list),
                                childAtPosition(
                                        withId(R.id.filter_layout),
                                        1)),
                        7),
                        isDisplayed()));
        linearLayout8.check(matches(isDisplayed()));

        ViewInteraction linearLayout9 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.filter_list),
                                childAtPosition(
                                        withId(R.id.filter_layout),
                                        1)),
                        8),
                        isDisplayed()));
        linearLayout9.check(matches(isDisplayed()));

        ViewInteraction linearLayout10 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.filter_list),
                                childAtPosition(
                                        withId(R.id.filter_layout),
                                        1)),
                        8),
                        isDisplayed()));
        linearLayout10.check(matches(isDisplayed()));

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
