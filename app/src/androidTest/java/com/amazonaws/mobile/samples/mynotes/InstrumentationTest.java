package com.amazonaws.mobile.samples.mynotes;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.GeneralLocation;
import android.support.test.espresso.action.GeneralSwipeAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Swipe;
import android.support.test.espresso.action.Swiper;
import android.support.test.espresso.contrib.RecyclerViewActions;
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

import java.util.UUID;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InstrumentationTest {

    @Rule
    public ActivityTestRule<AuthenticatorActivity> mActivityTestRule = new ActivityTestRule<>(AuthenticatorActivity.class);

    @Test
    public void authenticatorActivityTest() {
        try {
            Thread.sleep(10000);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        ViewInteraction textView = onView(
                allOf(withText("Sign-in"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Sign-in")));

        ViewInteraction textView2 = onView(
                allOf(withText("Create New Account"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.user_pool_sign_in_view_id),
                                        2),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Create New Account")));

        ViewInteraction textView3 = onView(
                allOf(withText("Forgot Your Password?"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.user_pool_sign_in_view_id),
                                        2),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("Forgot Your Password?")));

        ViewInteraction button = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.user_pool_sign_in_view_id),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1)),
                        1),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction editText = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("com.amazonaws.mobile.auth.userpools.FormView")),
                                0),
                        1),
                        isDisplayed()));
        editText.perform(replaceText("John"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("com.amazonaws.mobile.auth.userpools.FormEditText")),
                                1),
                        0),
                        isDisplayed()));
        editText2.perform(replaceText("Password123_"), closeSoftKeyboard());

        pressBack();

        ViewInteraction button2 = onView(
                allOf(withText("Sign In"),
                        childAtPosition(
                                allOf(withId(R.id.user_pool_sign_in_view_id),
                                        childAtPosition(
                                                withClassName(is("com.amazonaws.mobile.auth.ui.SignInView")),
                                                1)),
                                1),
                        isDisplayed()));
        button2.perform(click());
        try {
            Thread.sleep(10000);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        ViewInteraction imageButton = onView(
                allOf(withId(R.id.addNoteButton),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction textView4 = onView(
                allOf(withText("My Notes"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.app_bar),
                                                0)),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("My Notes")));

        onView(withId(R.id.note_list)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("AWS Pop-Up Loft")))).check(matches(isDisplayed()));
        onView(withText("AWS Pop-Up Loft")).perform(click());
        try {
            Thread.sleep(10000);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        ViewInteraction backButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.detail_toolbar),
                                        childAtPosition(
                                                withId(R.id.toolbar_layout),
                                                0)),
                                1),
                        isDisplayed()));


        allOf(withText("AWS Pop-Up Loft"), isDisplayed());
        allOf(withText("Welcome!"), isDisplayed());
        backButton.perform(click());
        try {
            Thread.sleep(10000);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.addNoteButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());
        try {
            Thread.sleep(10000);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.detail_toolbar),
                                        childAtPosition(
                                                withId(R.id.toolbar_layout),
                                                0)),
                                1),
                        isDisplayed()));

        ViewInteraction noteTitle = onView(
                allOf(withId(R.id.edit_title),
                        childAtPosition(
                                allOf(withId(R.id.note_detail),
                                        childAtPosition(
                                                withId(R.id.note_detail_container),
                                                0)),
                                0),
                        isDisplayed()));
        noteTitle.check(matches(withHint("Title")));

        ViewInteraction noteContent = onView(
                allOf(withId(R.id.edit_content),
                        childAtPosition(
                                allOf(withId(R.id.note_detail),
                                        childAtPosition(
                                                withId(R.id.note_detail_container),
                                                0)),
                                1),
                        isDisplayed()));
        noteContent.check(matches(withHint("Note Content")));


        String newTitle = UUID.randomUUID().toString();

        noteTitle.perform(replaceText(newTitle), closeSoftKeyboard());
        noteContent.perform(replaceText("A New Note"), closeSoftKeyboard());


        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        appCompatImageButton.perform(click());


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView5 = onView(withId(R.id.note_list)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText(newTitle)))).check(matches(isDisplayed()));

        onView(allOf(withText(newTitle))).check(matches(isDisplayed()));
        onView(allOf(withText(newTitle))).perform(new GeneralSwipeAction(Swipe.FAST, GeneralLocation.CENTER, GeneralLocation.CENTER_LEFT,Press.FINGER));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText(newTitle)).check(doesNotExist());

        try {
            Thread.sleep(2000);
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
