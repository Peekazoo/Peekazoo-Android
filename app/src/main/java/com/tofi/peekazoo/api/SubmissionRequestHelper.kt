package com.tofi.peekazoo.api

import com.tofi.peekazoo.di.components.ActivityComponent
import com.tofi.peekazoo.models.BaseSubmission
import com.tofi.peekazoo.models.inkbunny.InkbunnySearchResponse
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Derek on 19/05/2017.
 * Responsible for fetching submissions from all the apis and combining them in to o single list
 */
class SubmissionRequestHelper(component: ActivityComponent) {

    @Inject
    lateinit var inkbunnyApi: InkbunnyApi

    @Inject
    lateinit var weasylApi: WeasylApi

    init {
        component.inject(this)
    }

    /**
     * Fetch submissions from all apis and combine them in to one feed. Any api that returns an error
     * will not be added to the results and won't affect the other successful requests
     */
    fun fetchSubmissions(): Observable<MutableList<BaseSubmission>> {

        val inkbunnyObservable = inkbunnyApi.search(1)
                .subscribeOn(Schedulers.io())
                .onErrorReturn { InkbunnySearchResponse() }

        return inkbunnyObservable
                .map({ response ->
                    response.submissions as MutableList<BaseSubmission>
                })

        /*val weasylObservable = weasylApi.getSubmissions(30)
                .subscribeOn(Schedulers.io())
                .onErrorReturn { mutableListOf() }

        return inkbunnyObservable
                .zipWith(weasylObservable, BiFunction<InkbunnySearchResponse,
                        MutableList<WeasylSubmission>, MutableList<BaseSubmission>> { response1, response2 ->
                    val submissions = mutableListOf<BaseSubmission>()
                    submissions.addAll(response1.submissions)
                    submissions.addAll(response2)
                    submissions
                })*/
    }
}