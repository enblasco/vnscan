package es.verdnatura.core

import android.content.Context
import android.graphics.drawable.Drawable

/**
 * Created by Enrique Blasco Blanquer on 2/3/17.
 */
class UtilsResources {

    companion object{
        fun getResourceString(context: Context, resource: Int): String {
            return context.getString(resource)
        }

        fun getResourceInt(context: Context, resource: Int): Int {
            return context.resources.getInteger(resource)
        }

        fun getColor(context: Context, resource: Int): Int{
            return context.resources.getColor(resource)
        }

        fun getStringArray(context: Context, resource: Int): Array<out String>? {
            return context.resources.getStringArray(resource)
        }

        fun getDrawable(context: Context, resource: Int): Drawable{
            return context.resources.getDrawable(resource)
        }
    }

}