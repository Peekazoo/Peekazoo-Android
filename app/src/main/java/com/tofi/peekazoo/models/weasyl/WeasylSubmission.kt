package com.tofi.peekazoo.models.weasyl

import com.google.gson.annotations.SerializedName
import com.tofi.peekazoo.models.BaseSubmission

/**
 * Created by Derek on 19/05/2017.
 * Api response for a Weasyl submission
 */
data class WeasylSubmission(override var title: String = "",
                            var owner: String = "",
                            @SerializedName("submitid") var id: Long = -1,
                            override var timeCreated: Long = 0L,
                            var type: String = "",
                            var media: WeasylSubmissionMedia = WeasylSubmissionMedia()): BaseSubmission {

    override fun fetchThumbnailUrl(): String? {
        return if (media.thumbnail.isNotEmpty()) media.thumbnail[0].url else null
    }
}