package com.tofi.peekazoo.di.modules

import com.tofi.peekazoo.di.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val INKBUNNY_BASE_URL = "https://inkbunny.net"

/**
 * Created by Derek on 01/05/2017.
 * Modules providing objects that enable making networking calls
 */
@Module(includes = arrayOf(HttpModule::class))
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideRetrofit(httpClient : OkHttpClient) : Retrofit {

        return Retrofit.Builder()
                .client(httpClient)
                .baseUrl(INKBUNNY_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}