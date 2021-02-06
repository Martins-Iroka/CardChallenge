package com.example.cardchallenge

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.cardchallenge.util.EspressoIdlingResource
import com.example.domain.usecase.CardUseCase
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class CardInfoFinderActivityTest {

    private lateinit var cardUseCase: CardUseCase

    // An idling resource that waits for Data Binding to have no pending bindings.
    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun setUp() {
        cardUseCase = ServiceProvide.provideCardUseCase()

        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test
    fun getCardInfo_DisplayInUI()  {
        val activityScenario = ActivityScenario.launch(CardInfoFinderActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        onView(withId(R.id.cardNumberEntry)).perform(click()).perform(typeText("5199110730084073"),
            ViewActions.closeSoftKeyboard()
        )
        onView(withId(R.id.proceed)).perform(click())
        onView(withId(R.id.progress)).check(matches(isDisplayed()))
        onView(withId(R.id.card_info_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.card_brand)).check(matches(isDisplayed()))
        onView(withId(R.id.card_scheme)).check(matches(isDisplayed()))
        onView(withId(R.id.card_type)).check(matches(isDisplayed()))
        onView(withId(R.id.bank)).check(matches(isDisplayed()))
        onView(withId(R.id.country)).check(matches(isDisplayed()))

        activityScenario.close()
    }
}