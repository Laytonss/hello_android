package com.thoughtworks.helloworld_view

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.thoughtworks.helloworld_view.activity.LoginActivity
import com.thoughtworks.helloworld_view.activity.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @Test
    fun should_jump_to_login_page_when_click_() {
        val expectedIntent = allOf(
            hasComponent(LoginActivity::class.java.name),
            toPackage("com.thoughtworks.helloworld_view")
        )
        Espresso.onView(ViewMatchers.withText("LOGIN")).perform(ViewActions.click())
        intended(expectedIntent)
    }
}