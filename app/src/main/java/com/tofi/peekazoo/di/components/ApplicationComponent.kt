package com.tofi.peekazoo.di.components

import com.tofi.peekazoo.PeekazoApp
import com.tofi.peekazoo.di.ApplicationScope
import com.tofi.peekazoo.di.modules.*
import dagger.Component

/**
 * Created by Derek on 01/05/2017.
 * Dependency injection component for providing application level modules
 */
@ApplicationScope
@Component(modules = arrayOf(ApplicationModule::class, SharedPreferencesModule::class,
        HttpModule::class, NetworkModule::class, ApiModule::class))
interface ApplicationComponent {

    companion object {
        fun init(application: PeekazoApp) : ApplicationComponent {

            return DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(application))
                    .sharedPreferencesModule(SharedPreferencesModule(application))
                    .gsonModule(GsonModule())
                    .httpModule(HttpModule(application))
                    .networkModule(NetworkModule())
                    .apiModule(ApiModule())
                    .build()
        }
    }

    fun activityComponent() : ActivityComponent

    fun inject(application : PeekazoApp)
}
