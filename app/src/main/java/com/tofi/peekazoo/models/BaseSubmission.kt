package com.tofi.peekazoo.models

/**
 * Created by Derek on 19/05/2017.
 * Base definition for a submission. Provides access to common data
 */
interface BaseSubmission {

    fun fetchTitle(): String
    fun fetchThumbnailUrl(): String?
}