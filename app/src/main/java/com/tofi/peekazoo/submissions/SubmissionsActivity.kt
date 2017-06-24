package com.tofi.peekazoo.submissions

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import com.tofi.peekazoo.R
import com.tofi.peekazoo.SharedPreferencesManager
import com.tofi.peekazoo.api.InkbunnyApi
import com.tofi.peekazoo.api.SubmissionRequestHelper
import com.tofi.peekazoo.api.WeasylApi
import com.tofi.peekazoo.base.BaseDrawerActivity
import com.tofi.peekazoo.di.components.ActivityComponent
import com.tofi.peekazoo.di.modules.NetworkModule
import com.tofi.peekazoo.drawer.DrawerDefs
import com.tofi.peekazoo.drawer.NavDrawerScreen
import com.tofi.peekazoo.models.BaseSubmission
import com.tofi.peekazoo.utils.InkbunnyConverter
import com.tofi.peekazoo.utils.InkbunnyFailure
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_submissions.*
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Derek on 24/06/2017.
 * Screen showing a list of submissions.
 */
class SubmissionsActivity : BaseDrawerActivity() {

    @field: [Inject Named(NetworkModule.INKBUNNY)] lateinit var inkbunnyConverter: InkbunnyConverter
    @Inject lateinit var sharedPreferencesManager: SharedPreferencesManager
    @Inject lateinit var inkbunnyApi: InkbunnyApi
    @Inject lateinit var weasylApi: WeasylApi

    override lateinit var drawerHolder: DrawerLayout
    override lateinit var drawerScreen: NavDrawerScreen
    override lateinit var toolbar: Toolbar

    private lateinit var submissionRequestHelper: SubmissionRequestHelper
    private lateinit var searchRequestSuccess: Consumer<MutableList<BaseSubmission>>
    private lateinit var searchRequestFailure: InkbunnyFailure

    private var adapter: SubmissionResultsAdapter? = null
    private var layoutManager: StaggeredGridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submissions)
        setUpApiActions()

        drawerHolder = navDrawerHolder
        drawerScreen = navDrawer
        toolbar = toolbar_main
        submissionRequestHelper = SubmissionRequestHelper(activityComponent)

        val sid = sharedPreferencesManager.getStringPreference(SharedPreferencesManager.SESSION_ID, "")

        if (sid.isBlank()) {
            fetchInkbunnyToken()
        } else {
            startSearchRequest()
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        toolbar.title = DrawerDefs.SUBMISSIONS
    }

    override fun onResume() {
        super.onResume()

        drawerScreen.setSelectedRow(DrawerDefs.SUBMISSIONS)
    }

    override fun inject(component: ActivityComponent) {

        activityComponent.inject(this)
    }

    private fun setUpApiActions() {

        searchRequestSuccess = object: Consumer<MutableList<BaseSubmission>> {
            override fun accept(submissions: MutableList<BaseSubmission>) {
                if (adapter == null) {
                    adapter = SubmissionResultsAdapter(activityComponent, submissions)
                    layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    listSearchResults.adapter = adapter
                    listSearchResults.layoutManager = layoutManager

                    loadingView.visibility = View.GONE
                }
            }
        }

        searchRequestFailure = object: InkbunnyFailure(inkbunnyConverter) {
            override fun onNetworkError() {

            }

            override fun onError(errorMessage: String, statusCode: Int) {
                if (statusCode == 2) {
                    fetchInkbunnyToken()
                }
            }
        }
    }

    private fun fetchInkbunnyToken() {

        inkbunnyApi.login("guest", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({(sid, _, _) ->
                    sharedPreferencesManager.writeStringPreference(SharedPreferencesManager.SESSION_ID, sid)
                    startSearchRequest()
                }, {})
    }

    private fun startSearchRequest() {

        submissionRequestHelper.fetchSubmissions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchRequestSuccess, searchRequestFailure)
    }
}
