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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchFilterTest {

    @Rule
    public ActivityTestRule<SplashScreen> mActivityTestRule = new ActivityTestRule<>(SplashScreen.class);

    @Test
    public void searchFilterTest() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.auto_complete_ingredient), isDisplayed()));
        appCompatEditText.perform(replaceText("lettuce"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.add), withText("add"),
                        withParent(withId(R.id.ingredient_layout)),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.id_diet), withText("diet"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatCheckBox = onView(
                allOf(withId(R.id.checkbox), withText("Vegan"),
                        withParent(childAtPosition(
                                withId(R.id.filter_list),
                                3)),
                        isDisplayed()));
        appCompatCheckBox.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("Done")));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.id_cuisine), withText("cuisine"), isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatCheckBox2 = onView(
                allOf(withId(R.id.checkbox), withText("American"),
                        withParent(childAtPosition(
                                withId(R.id.filter_list),
                                11)),
                        isDisplayed()));
        appCompatCheckBox2.perform(click());


        ViewInteraction appCompatButton5 = onView(
                allOf(withId(android.R.id.button1), withText("Done")));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.id_type), withText("recipe type"), isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatCheckBox7 = onView(
                allOf(withId(R.id.checkbox), withText("Salad"),
                        withParent(childAtPosition(
                                withId(R.id.filter_list),
                                4)),
                        isDisplayed()));
        appCompatCheckBox7.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(android.R.id.button1), withText("Done")));
        appCompatButton7.perform(scrollTo(), click());

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.search_recipes), isDisplayed()));
        floatingActionButton.perform(click());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
