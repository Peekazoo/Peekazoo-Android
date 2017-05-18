package com.tofi.peekazoo.api

import com.tofi.peekazoo.models.InkbunnyLoginResponse
import com.tofi.peekazoo.models.InkbunnySearchResponse
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by Derek on 01/05/2017.
 * Contains Inkbunny api endpoints
 */
interface InkbunnyApi {

    @FormUrlEncoded
    @POST("api_login.php")
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<InkbunnyLoginResponse>

    @GET("api_search.php")
    fun search(@Query("page") page: Int): Observable<InkbunnySearchResponse>
}