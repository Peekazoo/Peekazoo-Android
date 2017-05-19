package com.tofi.peekazoo.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.tofi.peekazoo.R
import com.tofi.peekazoo.SharedPreferencesManager
import com.tofi.peekazoo.api.InkbunnyApi
import com.tofi.peekazoo.api.WeasylApi
import com.tofi.peekazoo.di.components.ActivityComponent
import com.tofi.peekazoo.lists.adapters.SubmissionResultsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_submissions.*
import javax.inject.Inject

class SubmissionsActivity : BaseActivity() {

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    @Inject
    lateinit var inkbunnyApi: InkbunnyApi

    @Inject
    lateinit var weasylApi: WeasylApi

    private var adapter: SubmissionResultsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submissions)

        val sid = sharedPreferencesManager.getStringPreference(SharedPreferencesManager.SESSION_ID, "")

        if (sid.isBlank()) {
            inkbunnyApi.login("guest", "")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({(sid, _, _) ->
                        sharedPreferencesManager.writeStringPreference(SharedPreferencesManager.SESSION_ID, sid)
                        startSearchRequest()
                    }, {})
        } else {
            startSearchRequest()
        }

        weasylApi.getSubmissions(30)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, {})
    }

    override fun inject(component: ActivityComponent) {

        activityComponent.inject(this)
    }

    private fun startSearchRequest() {

        inkbunnyApi.search(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({response ->
                    if (adapter == null) {
                        adapter = SubmissionResultsAdapter(activityComponent, response.submissions)
                        listSearchResults.adapter = adapter
                        listSearchResults.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    }
                }, {})
    }
}
