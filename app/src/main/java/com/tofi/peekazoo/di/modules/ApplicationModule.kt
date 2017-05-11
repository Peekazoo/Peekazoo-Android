package com.tofi.peekazoo.di.modules

import android.content.Context
import android.content.res.Resources
import com.tofi.peekazoo.PeekazoApp
import com.tofi.peekazoo.di.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * Created by Derek on 01/05/2017.
 * Modules providing basic application level objects that all classes can use
 */
@Module
class ApplicationModule(private val application : PeekazoApp) {

    @Provides
    @ApplicationScope
    fun provideApplicationContext() : Context {
        return application
    }

    @Provides
    @ApplicationScope
    fun provideApplicationResources() : Resources {
        return application.resources
    }
}
