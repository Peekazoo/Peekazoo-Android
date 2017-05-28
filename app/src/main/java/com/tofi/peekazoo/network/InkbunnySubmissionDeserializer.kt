package com.tofi.peekazoo.network

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.tofi.peekazoo.models.inkbunny.InkbunnySubmission
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Derek on 28/05/2017.
 * Deserializes an Inkbunny submission response
 */
class InkbunnySubmissionDeserializer(val gson: Gson): JsonDeserializer<InkbunnySubmission> {

    private var timeFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSX", Locale.getDefault())

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): InkbunnySubmission {

        val jsonObject = json.asJsonObject
        val createdTime = jsonObject.get("create_datetime").asString
        val date = timeFormatter.parse(createdTime)

        val submission = gson.fromJson(jsonObject.toString(), InkbunnySubmission::class.java)
        submission.timeCreated = date.time
        return submission
    }
}