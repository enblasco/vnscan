package es.verdnatura.core

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Enrique Blasco Blanquer on 5/7/17.
 */
class UtilsDateTime {

    companion object{
        private val dfDate = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        private val cal = Calendar.getInstance()

        fun getTodayString(): String{
            return dfDate.format(cal.time)
        }

        fun addDaysAndReturnDateString(days: Int): String{
            cal.add(Calendar.DAY_OF_MONTH, days)
            return dfDate.format(cal.time)
        }
    }

}