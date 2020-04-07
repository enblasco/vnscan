package es.verdnatura.vnsplits.domain.parser

import com.google.gson.Gson
import com.google.gson.JsonParser
import java.util.ArrayList
import javax.inject.Singleton


/**
 * Created by Enrique Blasco Blanquer on 8/3/17.
 */
@Singleton
class Serializer {

    private val gson = Gson()

    fun serialize(obj: Any, clazz: Class<*>): String {
        return gson.toJson(obj, clazz)
    }

    fun <T> deserialize(string: String, clazz: Class<T>): T {
        return gson.fromJson(string, clazz)
    }

    fun <T> deserializeList(string: String, clazz: Class<T>): List<T> {
        val parser = JsonParser()
        val array = parser.parse(string).getAsJsonArray()

        val lst = ArrayList<T>()
        for (json in array) {
            val entity = gson.fromJson<T>(json, clazz)
            lst.add(entity)
        }

        return lst
    }

}