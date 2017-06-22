package com.tofi.peekazoo.submissions

import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso
import com.tofi.peekazoo.di.components.ActivityComponent
import com.tofi.peekazoo.models.BaseSubmission
import kotlinx.android.synthetic.main.viewholder_submission.view.*
import javax.inject.Inject

/**
 * Created by Derek on 18/05/2017.
 * Displays a single submission in a list
 */
class SubmissionViewHolder(component: ActivityComponent, rootView: View): RecyclerView.ViewHolder(rootView) {

    @Inject
    lateinit var picasso: Picasso

    init {
        component.inject(this)
    }

    fun bindData(submission: BaseSubmission) {

        picasso.load(submission.fetchThumbnailUrl())
                .into(itemView.image_thumbnail)
        itemView.text_title.text = submission.title
    }
}