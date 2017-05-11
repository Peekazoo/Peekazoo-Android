package com.tofi.peekazoo

import android.app.Application
import android.content.res.Resources
import android.util.Log
import com.tofi.peekazoo.di.components.ApplicationComponent
import javax.inject.Inject

/**
 * Created by Derek on 01/05/2017.
 * Top level application instance
 */
class PeekazoApp : Application() {

    @Inject
    lateinit var applicationResources : Resources

    lateinit var component : ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = ApplicationComponent.init(this)
        component.inject(this)

        Log.d("Test", applicationResources.getString(R.string.app_name))
    }
}
