package com.tofi.peekazoo.api

import com.tofi.peekazoo.models.weasyl.WeasylSubmission
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Derek on 19/05/2017.
 * Defines Weasyl api endpoints
 */
interface WeasylApi {

    @GET("api/submissions/frontpage")
    fun getSubmissions(@Query("count") count: Int): Observable<MutableList<WeasylSubmission>>
}