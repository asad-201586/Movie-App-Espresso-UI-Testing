package com.codingwithmitch.espressouitestexamples.ui.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.codingwithmitch.espressouitestexamples.R
import com.codingwithmitch.espressouitestexamples.data.FakeMovieData
import com.codingwithmitch.espressouitestexamples.ui.movie.MoviesListAdapter.*
import com.codingwithmitch.espressouitestexamples.util.EspressoIdlingResourceRule
import com.google.android.material.internal.ContextUtils.getActivity
import org.hamcrest.CoreMatchers.not
import org.hamcrest.core.Is.`is`
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class MovieListFragmentTest{

    val LIST_ITEM_IN_TEST = 1
    val MOVIE_IN_TEST = FakeMovieData.movies[LIST_ITEM_IN_TEST]

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get: Rule
    val espressoIdlingResoureRule = EspressoIdlingResourceRule()


    @Test
    fun a_test_isListFragmentVisible_onAppLaunch() {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))

        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun test_selectListItem_isDetailFragmentVisible() {

        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))
    }

    @Test
    fun test_backNavigation_toMovieListFragment() {

        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))

        pressBack()

        // Confirm MovieListFragment in view
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_navDirectorsFragment_validateDirectorsList() {

        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))

        // Nav to DirectorsFragment
        onView(withId(R.id.movie_directiors)).perform(click())

        // Confirm correct directors are visible
        onView(withId(R.id.directors_text))
            .check(
                matches(
                    withText(
                        DirectorsFragment.stringBuilderForDirectors(MOVIE_IN_TEST.directors!!)
                    )
                )
            )
    }

    @Test
    fun test_navStarActorsFragment_validateActorsList() {

        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))

        // Nav to DirectorsFragment
        onView(withId(R.id.movie_star_actors)).perform(click())

        // Confirm correct directors are visible
        onView(withId(R.id.star_actors_text))
            .check(
                matches(
                    withText(
                        StarActorsFragment.stringBuilderForStarActors(MOVIE_IN_TEST.star_actors!!)
                    )
                )
            )
    }

    /**
     * not working
     */
    /*@Test
    fun test_showDialog_captureNameInput(){
        val EXPECTED_NAME = "Asad"

        // execute and verify
        onView(withId(R.id.button_dialog)).perform(click())
        onView(withText(R.string.enter_your_name)).check(matches(isDisplayed()))
        onView(withText(R.string.text_ok)).perform(click())
        onView(withText(R.string.enter_your_name)).check(matches(isDisplayed()))

        //enter some text
        onView(withId(R.id.md_input_message)).perform(typeText(EXPECTED_NAME))
        onView(withText(R.string.text_ok)).perform(click())

        // check if the dialog is gone
        onView(withText(R.string.enter_your_name)).check(doesNotExist())

        // check if the button has set name
        onView(withId(R.id.button_dialog)).check(matches(withText(EXPECTED_NAME)))

    }*/

    @Test
    fun test_dialogView(){

        val EXPECTED_NAME = "Asad"

        onView(withId(R.id.button_dialog)).perform(click())
        onView(withId(R.id.text_enter_your_name)).check(matches(isDisplayed()))
        onView(withId(R.id.text_ok)).perform(click())
        onView(withId(R.id.text_enter_your_name)).check(matches(isDisplayed()))

        //enter some text
        onView(withId(R.id.edt_enter_your_name)).perform(typeText(EXPECTED_NAME))
        onView(withId(R.id.text_ok)).perform(click())

        // check if the button has set name
        onView(withId(R.id.button_dialog)).check(matches(withText(EXPECTED_NAME)))

    }


}









