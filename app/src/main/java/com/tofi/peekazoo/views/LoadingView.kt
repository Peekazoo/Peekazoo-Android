package com.tofi.peekazoo.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.tofi.peekazoo.R

/**
 * Created by Derek on 21/05/2017.
 * Used to overlay content whilst it is loading. Just shows a simple progress spinner in the centre
 * of the view
 */
class LoadingView: FrameLayout {

    constructor(context: Context) : super(context) {
        setUpView()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        setUpView()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(context, attributeSet, defStyle) {
        setUpView()
    }

    private fun setUpView() {
        LayoutInflater.from(context).inflate(R.layout.view_loading, this, true)
    }
}