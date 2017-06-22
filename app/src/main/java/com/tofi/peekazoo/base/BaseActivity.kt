package com.tofi.peekazoo.base

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.tofi.peekazoo.PeekazooApp
import com.tofi.peekazoo.di.components.ActivityComponent

/**
 * Created by Derek on 01/05/2017.
 * Base for all activities in the app
 */
abstract class BaseActivity : AppCompatActivity() {

    protected abstract var toolbar: Toolbar
    lateinit protected var activityComponent : ActivityComponent

    abstract fun inject(component : ActivityComponent)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val application = application as PeekazooApp
        activityComponent = application.component.activityComponent()
        inject(activityComponent)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        toolbar.setTitleTextColor(Color.WHITE)

        super.onPostCreate(savedInstanceState)

    }
}