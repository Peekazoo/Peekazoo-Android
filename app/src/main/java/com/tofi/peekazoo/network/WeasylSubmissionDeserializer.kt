package com.tofi.peekazoo.network

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.tofi.peekazoo.models.weasyl.WeasylSubmission
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Derek on 28/05/2017.
 * Deserializes a Weasyl submission response
 */
class WeasylSubmissionDeserializer(val gson: Gson): JsonDeserializer<WeasylSubmission> {

    private val timeFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): WeasylSubmission {
        val jsonObject = json.asJsonObject
        val timeCreated = jsonObject.get("posted_at").asString
        val date = timeFormatter.parse(timeCreated)

        val submission = gson.fromJson(jsonObject.toString(), WeasylSubmission::class.java)
        submission.timeCreated = date.time
        return submission
    }
}