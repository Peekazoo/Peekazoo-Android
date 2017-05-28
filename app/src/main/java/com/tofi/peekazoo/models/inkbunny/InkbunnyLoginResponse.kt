package com.tofi.peekazoo.models.inkbunny

/**
 * Created by Derek on 18/05/2017.
 * Api response for an Inkbunny login
 */
data class InkbunnyLoginResponse(var sid: String = "",
                                 var userId: String = "",
                                 var ratingMask: String = "") {
}