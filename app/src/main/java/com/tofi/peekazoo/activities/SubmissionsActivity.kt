package com.tofi.peekazoo.activities

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.tofi.peekazoo.R
import com.tofi.peekazoo.SharedPreferencesManager
import com.tofi.peekazoo.api.InkbunnyApi
import com.tofi.peekazoo.api.SubmissionRequestHelper
import com.tofi.peekazoo.api.WeasylApi
import com.tofi.peekazoo.base.BaseDrawerActivity
import com.tofi.peekazoo.di.components.ActivityComponent
import com.tofi.peekazoo.drawer.DrawerDefs
import com.tofi.peekazoo.drawer.NavDrawerScreen
import com.tofi.peekazoo.lists.adapters.SubmissionResultsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_submissions.*
import javax.inject.Inject

class SubmissionsActivity : BaseDrawerActivity() {

    override lateinit var drawerHolder: DrawerLayout
    override lateinit var drawerScreen: NavDrawerScreen

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    @Inject
    lateinit var inkbunnyApi: InkbunnyApi

    @Inject
    lateinit var weasylApi: WeasylApi

    private lateinit var submissionRequestHelper: SubmissionRequestHelper

    private var adapter: SubmissionResultsAdapter? = null
    private var layoutManager: StaggeredGridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submissions)
        drawerHolder = navDrawerHolder
        drawerScreen = navDrawer
        submissionRequestHelper = SubmissionRequestHelper(activityComponent)

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
    }

    override fun onResume() {
        super.onResume()

        drawerScreen.getDataModule().setSelectedRow(DrawerDefs.SUBMISSIONS)
    }

    override fun inject(component: ActivityComponent) {

        activityComponent.inject(this)
    }

    private fun startSearchRequest() {

        submissionRequestHelper.fetchSubmissions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({submissions ->
                    if (adapter == null) {
                        adapter = SubmissionResultsAdapter(activityComponent, submissions)
                        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        listSearchResults.adapter = adapter
                        listSearchResults.layoutManager = layoutManager

                        loadingView.visibility = View.GONE
                    }
                })
    }
}
