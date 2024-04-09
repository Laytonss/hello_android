package com.thoughtworks.helloworld_view


import android.widget.Button
import com.thoughtworks.helloworld_view.activity.LoginActivity
import com.thoughtworks.helloworld_view.activity.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class MainActivityUITest {

    private lateinit var mainActivity: MainActivity
    private lateinit var loginActivity: LoginActivity

    @Before
    fun setUp() {
        mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().get()
        loginActivity = Robolectric.buildActivity(LoginActivity::class.java).create().get()
    }


    @Test
    fun `should jump to login page when click login button`() {
        val loginButton = mainActivity.findViewById<Button>()
        loginButton.performClick()
        val shadowActivity = shadowOf(mainActivity)
        val nextActivityIntent = shadowActivity.nextStartedActivity
        assertEquals(nextActivityIntent.component?.className, LoginActivity::class.java.name)
    }

    @Test
    fun `should select box when click remember me button`() {

    }
}