package com.tofi.peekazoo

import android.os.Bundle
import com.tofi.peekazoo.api.InkbunnyApi
import com.tofi.peekazoo.di.components.ActivityComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var sharedPreferencesManager : SharedPreferencesManager

    @Inject
    lateinit var inkbunnyApi : InkbunnyApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inkbunnyApi.login("guest", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({(sid, _, _) ->
                    sharedPreferencesManager.writeStringPreference(SharedPreferencesManager.SESSION_ID, sid)
                }, {})
    }

    override fun inject(component: ActivityComponent) {
        activityComponent.inject(this)
    }
}
