package com.tofi.peekazoo.network

import com.tofi.peekazoo.SharedPreferencesManager
import okhttp3.Interceptor
import okhttp3.Response

private const val AUTH_KEY = "sid"

/**
 * Created by Derek on 18/05/2017.
 * Intercepts all api requests to Inkbunny and adds any headers/params needed to them
 */
class InkbunnyNetworkInterceptor(val sharedPrefs: SharedPreferencesManager): Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {

        var request = chain?.request()
        val requestBuilder = request?.newBuilder()
        val sid = sharedPrefs.getStringPreference(SharedPreferencesManager.SESSION_ID, "")

        if (request?.method() == "GET" && sid.isNotBlank()) {
            val url = request.url()
                    .newBuilder()
                    .addQueryParameter(AUTH_KEY, sid)
                    .build()

            request = requestBuilder?.url(url)?.build()
        }

        return request?.let { chain?.proceed(it) } ?: Response.Builder().build()
    }
}