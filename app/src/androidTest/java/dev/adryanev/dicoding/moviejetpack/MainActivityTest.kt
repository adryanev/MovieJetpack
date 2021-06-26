package dev.adryanev.dicoding.moviejetpack

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.adryanev.dicoding.moviejetpack.ui.main.MainActivity
import dev.adryanev.dicoding.moviejetpack.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {


    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(swipeUp())
    }

    @Test
    fun loadTvShows() {
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(swipeUp())
    }

    @Test
    fun loadDetailFromMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.judul_film)).check(matches(isDisplayed()))
        onView(withId(R.id.genre_text)).check(matches(isDisplayed()))
        onView(withId(R.id.tahun_rilis)).check(matches(isDisplayed()))
        onView(withId(R.id.ratingBar)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailFromTvShow() {
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withId(R.id.rv_movie)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.judul_film)).check(matches(isDisplayed()))
        onView(withId(R.id.genre_text)).check(matches(isDisplayed()))
        onView(withId(R.id.tahun_rilis)).check(matches(isDisplayed()))
        onView(withId(R.id.ratingBar)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
    }

    fun loadMovieFavorite() {

    }
}