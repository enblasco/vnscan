package es.verdnatura.vnsplits.test.framework

import android.app.Activity
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.matcher.ViewMatchers.*

/**
 * Created by Enrique Blasco Blanquer on 22/3/17.
 */
class Matchers {

    fun <T : Activity> nextOpenActivityIs(clazz: Class<T>) {
        intended(IntentMatchers.hasComponent(clazz.name))
    }

    fun viewIsVisibleAndContainsText(@StringRes stringResource: Int) {
        onView(withText(stringResource)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    fun viewContainsText(@IdRes viewId: Int, @StringRes stringResource: Int) {
        onView(withId(viewId)).check(matches(withText(stringResource)))
    }

    fun viewContainsText(@IdRes viewId: Int, text: String){
        onView(withId(viewId)).check(matches(withText(text)))
    }

    fun viewExist(@IdRes viewId: Int){
        onView(withId(viewId)).check(matches(isDisplayed()))
    }

    fun viewIsVisible(@IdRes viewId: Int){
        onView(withId(viewId)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    fun viewIsGone(@IdRes viewId: Int){
        onView(withId(viewId)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

}