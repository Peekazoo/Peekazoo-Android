package com.tofi.peekazoo.models

/**
 * Created by Derek on 18/05/2017.
 * Api response for a submission from Inkbunny
 */
data class InkbunnySubmission(var submissionId: String = "",
                              var title: String = "",
                              var submissionTypeId: String = "",
                              var thumbnail_url_medium: String? = null,
                              var thumbnail_url_medium_noncustom: String? = null,
                              var friends_only: String = "",
                              var guest_block: String = "",
                              var username: String = "",
                              var user_id: String = "") {

    fun getMediumThumbnailUrl(): String? {

        return thumbnail_url_medium?: thumbnail_url_medium_noncustom
    }
}