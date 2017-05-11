package com.tofi.peekazoo.api

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Derek on 01/05/2017.
 * Contains Inkbunny api endpoints
 */
interface InkbunnyApi {

    @FormUrlEncoded
    @POST("api_login.php")
    fun login(@Field("username") username : String, @Field("password") password : String) : Observable<Any>
}