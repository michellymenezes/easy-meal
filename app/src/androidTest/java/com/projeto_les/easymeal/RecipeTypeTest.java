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
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecipeTypeTest {

    @Rule
    public ActivityTestRule<SplashScreen> mActivityTestRule = new ActivityTestRule<>(SplashScreen.class);

    @Test
    public void recipeTypeTest() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.id_type), withText("recipe type"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatCheckBox = onView(
                allOf(withId(R.id.checkbox), withText("Main Course"),
                        withParent(childAtPosition(
                                withId(R.id.filter_list),
                                0)),
                        isDisplayed()));
        appCompatCheckBox.perform(click());

        ViewInteraction appCompatCheckBox2 = onView(
                allOf(withId(R.id.checkbox), withText("Appetizer"),
                        withParent(childAtPosition(
                                withId(R.id.filter_list),
                                3)),
                        isDisplayed()));
        appCompatCheckBox2.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.select_all_filters), withText("CHECK ALL"),
                        withParent(withId(R.id.filter_layout)),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.select_all_filters), withText("uncheck all"),
                        withParent(withId(R.id.filter_layout)),
                        isDisplayed()));
        appCompatButton3.perform(click());


        ViewInteraction appCompatCheckBox5 = onView(
                allOf(withId(R.id.checkbox), withText("Appetizer"),
                        withParent(childAtPosition(
                                withId(R.id.filter_list),
                                3)),
                        isDisplayed()));
        appCompatCheckBox5.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(android.R.id.button1), withText("Done")));
        appCompatButton6.perform(scrollTo(), click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.id_type), withText("recipe type"), isDisplayed()));
        appCompatButton7.perform(click());

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
