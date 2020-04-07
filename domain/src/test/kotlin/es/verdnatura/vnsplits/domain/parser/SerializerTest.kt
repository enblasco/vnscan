package es.verdnatura.vnsplits.data.check

import com.google.gson.annotations.SerializedName
import es.verdnatura.vnsplits.domain.parser.Serializer
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Enrique Blasco Blanquer on 21/3/17.
 */
class SerializerTest {

    lateinit var user: User
    lateinit var serializer: Serializer
    private val JSON_RESPONSE = "{\"Name\":\"James Olsen\",\"Age\":21,\"Address\":\"Street of winter\"}"

    @Before
    fun setUp(){
        user = User()
        user.Name = "James Olsen"
        user.Age = 21
        user.Address = "Street of winter"
        serializer = Serializer()
    }

    @Test
    fun testSerialize(){
        val response = serializer.serialize(user, User::class.java)
        assertEquals(response, JSON_RESPONSE)
    }

    @Test
    fun testDeserialize(){
        val newUser = serializer.deserialize(JSON_RESPONSE, User::class.java)
        assertEquals(newUser.Name, user.Name)
        assertEquals(newUser.Age, user.Age)
        assertEquals(newUser.Address, user.Address)
    }

    class User{
        @SerializedName("Name")
        var Name: String = ""

        @SerializedName("Age")
        var Age: Int = 0

        @SerializedName("Address")
        var Address: String = ""
    }

}