package com.tofi.peekazoo.di.modules

import android.content.Context
import com.tofi.peekazoo.BuildConfig
import com.tofi.peekazoo.SharedPreferencesManager
import com.tofi.peekazoo.di.ApplicationScope
import com.tofi.peekazoo.network.InkbunnyNetworkInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Named

const val TIMEOUT_DURATION = 60L

/**
 * Created by Derek on 01/05/2017.
 * Modules providing http elements for networking layer
 */
@Module(includes = arrayOf(SharedPreferencesModule::class))
class HttpModule(private val context : Context) {

    @Provides
    @ApplicationScope
    @Named(NetworkModule.INKBUNNY)
    fun provideOkHttpClient(builder: OkHttpClient.Builder, sharedPrefs: SharedPreferencesManager) : OkHttpClient {

        builder.addInterceptor(InkbunnyNetworkInterceptor(sharedPrefs))
        return builder.build()
    }

    @Provides
    @ApplicationScope
    fun provideLoggingInterceptor() : HttpLoggingInterceptor {

        val interceptor = HttpLoggingInterceptor()
        val logLevel = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor.setLevel(logLevel)
    }

    @Provides
    @ApplicationScope
    fun provideBaseOkHttpBuilder(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder {

        val builder = OkHttpClient.Builder()
        builder.connectTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
        builder.readTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
        builder.writeTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)

        builder.addInterceptor(loggingInterceptor)
        return builder
    }
}
