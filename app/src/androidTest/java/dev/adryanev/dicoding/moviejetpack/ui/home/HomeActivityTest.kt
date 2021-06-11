package dev.adryanev.dicoding.moviejetpack.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dummyMovies = DataDummy.generateMovies()
    private val dummyTvShows = DataDummy.generateTvShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeFragment::class.java)

    @Test
    fun loadMovies(){
        onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }
    @Test
    fun loadTvShows() {
        onView(ViewMatchers.withText("TV Show")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvshow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tvshow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
    }
    @Test
    fun loadDetailFromMovie(){
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.backdrop)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.judul_film)).check(ViewAssertions.matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.genre_text)).check(ViewAssertions.matches(withText(dummyMovies[0].genre?.joinToString(", "))))
        onView(withId(R.id.tahun_rilis)).check(ViewAssertions.matches(withText(dummyMovies[0].releaseDate)))
        onView(withId(R.id.ratingBar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.duration_text)).check(ViewAssertions.matches(withText("${dummyMovies[0].duration} Minutes")))
        onView(withId(R.id.country_text)).check(ViewAssertions.matches(withText(dummyMovies[0].language)))
        onView(withId(R.id.overview)).check(ViewAssertions.matches(withText(dummyMovies[0].overview)))
    }
    @Test
    fun loadDetailFromTvShow(){
        onView(withText("TV Show")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        onView(withId(R.id.backdrop)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.judul_film)).check(ViewAssertions.matches(withText(dummyTvShows[0].title)))
        onView(withId(R.id.genre_text)).check(ViewAssertions.matches(withText(dummyTvShows[0].genre?.joinToString(", "))))
        onView(withId(R.id.tahun_rilis)).check(ViewAssertions.matches(withText(dummyTvShows[0].releaseDate)))
        onView(withId(R.id.ratingBar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.duration_text)).check(ViewAssertions.matches(withText("${dummyTvShows[0].duration} Minutes")))
        onView(withId(R.id.country_text)).check(ViewAssertions.matches(withText(dummyTvShows[0].language)))
        onView(withId(R.id.overview)).check(ViewAssertions.matches(withText(dummyTvShows[0].overview)))
    }
}