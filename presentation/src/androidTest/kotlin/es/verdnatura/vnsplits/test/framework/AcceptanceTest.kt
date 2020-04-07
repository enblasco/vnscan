package es.verdnatura.vnsplits.test.framework

import android.app.Activity
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * Created by Enrique Blasco Blanquer on 22/3/17.
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
abstract class AcceptanceTest<T : Activity>(clazz: Class<T>, initActivity: Boolean = true) {

    @Rule @JvmField
    val testRule: ActivityTestRule<T> = IntentsTestRule(clazz, false, initActivity)

    val checkThat: Matchers = Matchers()
    val events: Events = Events()
}

