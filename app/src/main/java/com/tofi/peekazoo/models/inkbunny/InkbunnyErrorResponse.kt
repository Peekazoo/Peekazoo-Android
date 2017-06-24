package com.tofi.peekazoo.models.inkbunny

/**
 * Created by Derek on 24/06/2017.
 * Error response returned by Inkbunny api
 */
data class InkbunnyErrorResponse(var errorCode: Int = -1,
                                 var errorMessage: String = "") {
}