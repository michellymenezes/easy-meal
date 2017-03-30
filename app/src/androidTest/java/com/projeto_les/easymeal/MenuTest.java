package com.projeto_les.easymeal;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import com.projeto_les.easymeal.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MenuTest {

    @Rule
    public ActivityTestRule<SplashScreen> mActivityTestRule = new ActivityTestRule<>(SplashScreen.class);

    @Test
    public void menuTest() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open"),
                        withParent(allOf(withId(R.id.nav_action),
                                withParent(withId(R.id.content_layout)))),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Tutorial"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open"),
                        withParent(allOf(withId(R.id.nav_action),
                                withParent(withId(R.id.content_layout)))),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatCheckedTextView2 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("About"), isDisplayed()));
        appCompatCheckedTextView2.perform(click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withContentDescription("Open"),
                        withParent(allOf(withId(R.id.nav_action),
                                withParent(withId(R.id.content_layout)))),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction appCompatCheckedTextView3 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Select Ingredients"), isDisplayed()));
        appCompatCheckedTextView3.perform(click());

    }

}
