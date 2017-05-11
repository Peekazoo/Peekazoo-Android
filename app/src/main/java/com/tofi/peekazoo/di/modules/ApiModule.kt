package com.tofi.peekazoo.di.modules

import com.tofi.peekazoo.di.ApplicationScope
import com.tofi.peekazoo.api.InkbunnyApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Derek on 01/05/2017.
 */
@Module(includes = arrayOf(NetworkModule::class))
class ApiModule {

    @Provides
    @ApplicationScope
    fun provideInkBunnyApi(retrofit : Retrofit) : InkbunnyApi {

        return retrofit.create(InkbunnyApi::class.java)
    }
}