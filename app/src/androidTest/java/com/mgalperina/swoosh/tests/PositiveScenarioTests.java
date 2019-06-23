package com.mgalperina.swoosh.tests;


import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import com.mgalperina.swoosh.Controller.WelcomeActivity;
import com.mgalperina.swoosh.R;
import com.mgalperina.swoosh.Utilities.CountingIdlingResourceService;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static com.mgalperina.swoosh.Utilities.ConstantsKt.FINISH_ACTIVITY_IDLING_RESOURCE;

@RunWith(AndroidJUnit4.class)
public class PositiveScenarioTests {

    @Rule
    public ActivityTestRule<WelcomeActivity> loginActivityRule = new ActivityTestRule<>(WelcomeActivity.class);

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(
                CountingIdlingResourceService.get(FINISH_ACTIVITY_IDLING_RESOURCE));
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(
                CountingIdlingResourceService.get(FINISH_ACTIVITY_IDLING_RESOURCE));
    }

    @Test
    public void emptyTest() {

    }

    @Test
    public void endToEndGoThroughTest() {
        onView(ViewMatchers.withId(R.id.getStartedButton)).perform(click());
        onView(withId(R.id.mensLeagueBtn)).perform(click());
        onView(withId(R.id.leagueNextButton)).perform(click());
        onView(withId(R.id.ballerClickBtn)).perform(click());
        onView(withId(R.id.skillFinishBtn)).perform(click());
       // onView(withId(R.id.searchLeaguesText)).check(matches(isDisplayed()));

    }


}


