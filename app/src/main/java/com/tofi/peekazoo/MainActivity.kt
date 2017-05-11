package com.tofi.peekazoo

import android.os.Bundle
import com.tofi.peekazoo.api.InkbunnyApi
import com.tofi.peekazoo.di.components.ActivityComponent
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var sharedPreferencesManager : SharedPreferencesManager

    @Inject
    lateinit var inkbunnyApi : InkbunnyApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun inject(component: ActivityComponent) {
        activityComponent.inject(this)
    }
}
