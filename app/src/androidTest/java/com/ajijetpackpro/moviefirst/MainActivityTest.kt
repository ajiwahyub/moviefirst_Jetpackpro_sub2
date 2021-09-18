package com.ajijetpackpro.moviefirst

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ajijetpackpro.moviefirst.data.dummydata.MovieDummy
import com.ajijetpackpro.moviefirst.ui.home.MainActivity
import com.ajijetpackpro.moviefirst.utils.IdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val movieDummy = MovieDummy.getDummyMovie()
    private val tvshowDummy = MovieDummy.getDummyTvshow()
    private val movieDetailDummy = MovieDummy.getDummyDetailMovie()
    private val tvshowDetailDummy = MovieDummy.getDummyDetailTvshow()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(IdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.idlingResource)
    }

    @Test
    fun loadMovie(){
        Espresso.onView(withId(R.id.recycleViewMovie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.recycleViewMovie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movieDummy.size))
    }

    @Test
    fun loadTvshow(){
        Espresso.onView(withText(R.string.tabtvshos)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.recycleViewTvshow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.recycleViewTvshow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvshowDummy.size))
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(withId(R.id.recycleViewMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        Espresso.onView(withId(R.id.tv_detail_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_title))
            .check(ViewAssertions.matches(withText(movieDetailDummy[0].title)))
        Espresso.onView(withId(R.id.tv_detail_genre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_genre))
            .check(ViewAssertions.matches(withText(movieDetailDummy[0].genre?.joinToString())))
        Espresso.onView(withId(R.id.tv_detail_overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_overview))
            .check(ViewAssertions.matches(withText(movieDetailDummy[0].overview)))
        Espresso.onView(withId(R.id.tv_detail_userscore))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_userscore))
            .check(ViewAssertions.matches(withText(movieDetailDummy[0].uScore.toString())))
        Espresso.onView(withId(R.id.tv_detail_rdate))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_rdate))
            .check(ViewAssertions.matches(withText(movieDetailDummy[0].rDate)))
        Espresso.onView(withId(R.id.iv_detail_bg))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.iv_detail_poster))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadDetailTvshow() {
        Espresso.onView(withText(R.string.tabtvshos)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.recycleViewTvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        Espresso.onView(withId(R.id.tv_detail_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_title))
            .check(ViewAssertions.matches(withText(tvshowDetailDummy[0].title)))
        Espresso.onView(withId(R.id.tv_detail_genre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_genre))
            .check(ViewAssertions.matches(withText(tvshowDetailDummy[0].genre?.joinToString())))
        Espresso.onView(withId(R.id.tv_detail_overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_overview))
            .check(ViewAssertions.matches(withText(tvshowDetailDummy[0].overview)))
        Espresso.onView(withId(R.id.tv_detail_userscore))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_userscore))
            .check(ViewAssertions.matches(withText(tvshowDetailDummy[0].uScore.toString())))
        Espresso.onView(withId(R.id.tv_detail_rdate))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_rdate))
            .check(ViewAssertions.matches(withText(tvshowDetailDummy[0].rDate)))
        Espresso.onView(withId(R.id.iv_detail_bg))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.iv_detail_poster))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}