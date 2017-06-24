package com.tofi.peekazoo.network

import com.tofi.peekazoo.utils.InkbunnyConverter
import io.reactivex.functions.Consumer
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Derek on 24/06/2017.
 * Action for handling failed Inkbunny api requests
 */
abstract class InkbunnyFailureAction<T: Throwable>(val converter: InkbunnyConverter): Consumer<T> {

    protected abstract fun onNetworkError()
    protected abstract fun onError(errorMessage: String, statusCode: Int)

    override fun accept(error: T) {

        if (error is IOException) {
            onNetworkError()

        } else {
            try {
                val exception = error as HttpException
                val responseBody = exception.response().errorBody()
                val errorResponse = converter.convert(responseBody)
                onError(errorResponse.errorMessage, errorResponse.errorCode)

            } catch (exc: Exception) {
                onError("", -1)
            }
        }
    }
}