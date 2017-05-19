package com.tofi.peekazoo.models.weasyl

/**
 * Created by Derek on 19/05/2017.
 * Weasyl api response for a list of all media attached to a submission
 */
data class WeasylSubmissionMedia(var thumbnail: MutableList<WeasylMedia> = mutableListOf(),
                                 var submission: MutableList<WeasylMedia> = mutableListOf(),
                                 var cover: MutableList<WeasylMedia> = mutableListOf()) {
}