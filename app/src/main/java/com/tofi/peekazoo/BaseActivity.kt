package com.tofi.peekazoo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tofi.peekazoo.di.components.ActivityComponent

/**
 * Created by Derek on 01/05/2017.
 */
abstract class BaseActivity : AppCompatActivity() {

    lateinit protected var activityComponent : ActivityComponent

    abstract fun inject(component : ActivityComponent)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val application = application as PeekazoApp
        activityComponent = application.component.activityComponent()
        inject(activityComponent)
    }
}