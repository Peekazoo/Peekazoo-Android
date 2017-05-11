package com.tofi.peekazoo.di.modules

import android.content.Context
import com.tofi.peekazoo.SharedPreferencesManager
import com.tofi.peekazoo.di.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * Created by Derek on 06/05/2017.
 * Provides shared preferences dependencies
 */
@Module
class SharedPreferencesModule(private val context : Context) {

    @Provides
    @ApplicationScope
    fun provideSharedPreferencesManager() : SharedPreferencesManager {

        return SharedPreferencesManager(context)
    }
}