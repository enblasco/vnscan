package es.verdnatura.vnsplits.data.preferences

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Enrique Blasco Blanquer on 23/6/17.
 */
@Singleton
class Preferences {

    private val USER = "USER"
    private val PASS = "PASS"

    val preferences: SharedPreferences

    @Inject
    constructor(preferences: SharedPreferences){
        this.preferences = preferences
    }

    fun saveUserAndPass(user: String, pass: String){
        preferences.edit().putString(USER, user).apply()
        preferences.edit().putString(PASS, pass).apply()
    }

    fun getUser(): String{
        return preferences.getString(USER, "")
    }

    fun getPass(): String{
        return preferences.getString(PASS, "")
    }

    fun removeUserAndPass(){
        preferences.edit().remove(USER).apply()
        preferences.edit().remove(PASS).apply()
    }

}