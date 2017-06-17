package com.tofi.peekazoo.models

/**
 * Created by Derek on 19/05/2017.
 * Base definition for a submission. Provides access to common data
 */
interface BaseSubmission {

    var title: String
    var timeCreated: Long

    fun fetchThumbnailUrl(): String?
    fun getThumbnailSizeX(): Int
    fun getThumbnailSizeY(): Int
}