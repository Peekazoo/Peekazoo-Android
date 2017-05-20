package com.tofi.peekazoo.di.components

import com.tofi.peekazoo.activities.SubmissionsActivity
import com.tofi.peekazoo.api.SubmissionRequestHelper
import com.tofi.peekazoo.di.ActivityScope
import com.tofi.peekazoo.lists.viewholders.SubmissionViewHolder
import dagger.Subcomponent

/**
 * Created by Derek on 01/05/2017.
 * Dependency injection component for providing application level modules
 */
@ActivityScope
@Subcomponent
interface ActivityComponent {

    fun inject(submissionsActivity: SubmissionsActivity)

    fun inject(submissionViewHolder: SubmissionViewHolder)

    fun inject(submissionRequestHelper: SubmissionRequestHelper)
}