package com.tofi.peekazoo.models

/**
 * Created by Derek on 18/05/2017.
 * Api response for a submission from Inkbunny
 */
data class InkbunnySubmission(var submissionId: String = "",
                              var title: String = "",
                              var submissionTypeId: String = "",
                              var thumbnailUrlMedium: String? = null,
                              var thumbnailUrlMediumNoncustom: String? = null,
                              var thumbnailUrlLarge: String? = null,
                              var thumbnailUrlLargeNoncustom: String? = null,
                              var thumbnailUrlHuge: String? = null,
                              var thumbnailUrlHugeNoncustom: String? = null,
                              var friendsOnly: String = "",
                              var guestBlock: String = "",
                              var username: String = "",
                              var userId: String = ""): BaseSubmission {

    override fun fetchTitle(): String {
        return title
    }

    override fun fetchThumbnailUrl(): String? {
        return thumbnailUrlHuge?: thumbnailUrlHugeNoncustom
    }
}