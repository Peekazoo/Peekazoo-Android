package com.tofi.peekazoo.utils

import com.tofi.peekazoo.models.inkbunny.InkbunnyErrorResponse
import com.tofi.peekazoo.network.InkbunnyFailureAction
import okhttp3.ResponseBody
import retrofit2.Converter

/**
 * Created by Derek on 24/06/2017.
 * Alias definitions used in the app
 */

typealias InkbunnyConverter = Converter<ResponseBody, InkbunnyErrorResponse>
typealias InkbunnyFailure = InkbunnyFailureAction<Throwable>