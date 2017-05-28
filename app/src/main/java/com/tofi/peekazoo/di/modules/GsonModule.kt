package com.tofi.peekazoo.di.modules

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tofi.peekazoo.di.ApplicationScope
import com.tofi.peekazoo.models.inkbunny.InkbunnySubmission
import com.tofi.peekazoo.models.weasyl.WeasylSubmission
import com.tofi.peekazoo.network.InkbunnySubmissionDeserializer
import com.tofi.peekazoo.network.WeasylSubmissionDeserializer
import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Derek on 18/05/2017.
 * Provides Gson dependencies
 */
@Module
class GsonModule {

    private val basicGson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    @Provides @ApplicationScope
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {

        return GsonConverterFactory.create(gson)
    }

    @Provides @ApplicationScope
    fun provideGson(): Gson {

        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(InkbunnySubmission::class.java, InkbunnySubmissionDeserializer(basicGson))
                .registerTypeAdapter(WeasylSubmission::class.java, WeasylSubmissionDeserializer(basicGson))
                .create()
    }
}