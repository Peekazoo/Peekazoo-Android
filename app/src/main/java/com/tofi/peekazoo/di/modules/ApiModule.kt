package com.tofi.peekazoo.di.modules

import com.tofi.peekazoo.api.InkbunnyApi
import com.tofi.peekazoo.di.ApplicationScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Created by Derek on 01/05/2017.
 * Provides apis the app can interact with
 */
@Module(includes = arrayOf(NetworkModule::class))
class ApiModule {

    @Provides
    @ApplicationScope
    fun provideInkBunnyApi(@Named(NetworkModule.INKBUNNY) retrofit : Retrofit) : InkbunnyApi {

        return retrofit.create(InkbunnyApi::class.java)
    }
}