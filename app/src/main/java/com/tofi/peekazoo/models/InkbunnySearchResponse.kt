package com.tofi.peekazoo.models

/**
 * Created by Derek on 18/05/2017.
 * Api response from Inkbunny for a search
 */
data class InkbunnySearchResponse(var submissions: MutableList<InkbunnySubmission> = mutableListOf()) {
}