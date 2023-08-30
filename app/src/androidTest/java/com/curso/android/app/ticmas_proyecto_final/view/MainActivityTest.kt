package com.curso.android.app.ticmas_proyecto_final.view

import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.curso.android.app.ticmas_proyecto_final.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class  MainActivityTest {
    @get: Rule
    var rule: ActivityScenarioRule<*> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivity_compareTexts(){

        Espresso.onView(ViewMatchers.withId(R.id.textInput1))
            .perform(ViewActions.typeText("ABC"))

        Espresso.onView(ViewMatchers.withId(R.id.textInput2))
            .perform(ViewActions.typeText("ABC"))

        Espresso.onView(ViewMatchers.withId(R.id.compareButton))
            .perform(ViewActions.click())

        Espresso.onView(
            ViewMatchers.withId(R.id.textResult)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText("Los textos son iguales")
            )
        )
    }
}