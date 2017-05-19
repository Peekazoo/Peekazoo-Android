package com.tofi.peekazoo.models.weasyl

import com.google.gson.annotations.SerializedName
import com.tofi.peekazoo.models.BaseSubmission

/**
 * Created by Derek on 19/05/2017.
 * Api response for a Weasyl submission
 */
data class WeasylSubmission(var title: String = "",
                            var owner: String = "",
                            @SerializedName("submitid") var id: Long = -1,
                            var type: String = "",
                            var media: WeasylSubmissionMedia = WeasylSubmissionMedia()): BaseSubmission {

    override fun fetchTitle(): String {
        return title
    }

    override fun fetchThumbnailUrl(): String? {
        return if (media.thumbnail.isNotEmpty()) media.thumbnail[0].url else null
    }
}