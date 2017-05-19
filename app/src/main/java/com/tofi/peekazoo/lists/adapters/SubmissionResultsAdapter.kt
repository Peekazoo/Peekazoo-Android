package com.tofi.peekazoo.lists.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tofi.peekazoo.R
import com.tofi.peekazoo.di.components.ActivityComponent
import com.tofi.peekazoo.lists.viewholders.SubmissionViewHolder
import com.tofi.peekazoo.models.BaseSubmission
import com.tofi.peekazoo.models.InkbunnySubmission

/**
 * Created by Derek on 18/05/2017.
 * Adapter populating a list of submission results
 */
class SubmissionResultsAdapter(val component: ActivityComponent,
                               val submissions: MutableList<BaseSubmission> = mutableListOf()):
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent?.context)

        return SubmissionViewHolder(component, inflater.inflate(R.layout.viewholder_submission, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        if (holder is SubmissionViewHolder) {
            holder.bindData(submissions[position])
        }
    }

    override fun getItemCount(): Int {

        return submissions.size
    }
}