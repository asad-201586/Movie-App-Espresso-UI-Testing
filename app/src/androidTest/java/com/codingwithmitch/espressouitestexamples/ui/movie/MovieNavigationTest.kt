package com.codingwithmitch.espressouitestexamples.ui.movie

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.codingwithmitch.espressouitestexamples.R
import com.codingwithmitch.espressouitestexamples.data.FakeMovieData
import com.codingwithmitch.espressouitestexamples.util.EspressoIdlingResourceRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieNavigationTest {

    private val LIST_ITEM_IN_TEST = 1
    val MOVIE_IN_TEST = FakeMovieData.movies[LIST_ITEM_IN_TEST]

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get: Rule
    val espressoIdlingResoureRule = EspressoIdlingResourceRule()

    @Test
    fun test_recyclerViewVisibility(){
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_recyclerItemClickEvent(){
        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition<MoviesListAdapter.MovieViewHolder>(
                    LIST_ITEM_IN_TEST,
                    click()
                )
            )

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.movie_title)).check(matches(ViewMatchers.withText(MOVIE_IN_TEST.title)))

        pressBack()

        // is recyclerview displayed
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    /*@Test
    fun testMovieFragmentNavigation(){
        // setup
        //val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        val scenario = launchFragmentInContainer<MovieDetailFragment>()

        // Nav DirectorsFragment
        onView(withId(R.id.movie_directiors)).perform(click())

        // Verify
        onView(withId(R.id.fragment_movie_directors_parent)).check(matches(isDisplayed()))

        pressBack()

        // Verify
        onView(withId(R.id.fragment_movie_detail_parent)).check(matches(isDisplayed()))

        // nav StartActorsFragment
        onView(withId(R.id.movie_star_actors)).perform(click())

        // verify
        onView(withId(R.id.fragment_movie_star_actors)).check(matches(isDisplayed()))

    }*/

}