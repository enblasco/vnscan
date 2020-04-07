package es.verdnatura.vnsplits.test.framework

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.v7.widget.RecyclerView


/**
 * Created by Enrique Blasco Blanquer on 22/3/17.
 */
class Events {

    fun clickOnView(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(click())
    }

    fun typeTextOnView(@IdRes viewId: Int, text: String){
        onView(withId(viewId)).perform(typeText(text))
    }

    fun <T : RecyclerView.ViewHolder>clickItemRecyclerView(@IdRes viewId: Int, position: Int){
        onView(withId(viewId)).perform(RecyclerViewActions.actionOnItemAtPosition<T>(position, click()))
    }
}