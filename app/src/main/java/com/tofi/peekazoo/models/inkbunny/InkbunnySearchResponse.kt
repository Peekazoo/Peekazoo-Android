package com.tofi.peekazoo.models.inkbunny

import com.tofi.peekazoo.models.inkbunny.InkbunnySubmission

/**
 * Created by Derek on 18/05/2017.
 * Api response from Inkbunny for a search
 */
data class InkbunnySearchResponse(var submissions: MutableList<InkbunnySubmission> = mutableListOf()) {
}