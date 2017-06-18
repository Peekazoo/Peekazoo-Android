package com.tofi.peekazoo.drawer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.widget.FrameLayout
import com.tofi.peekazoo.R

/**
 * Created by Derek on 18/06/2017.
 * A layout that draws something in the insets passed to fitSystemWindows(Rect), i.e. the area above
 * UI chrome (status and navigation bars, overlay action bars).
 */
class ScrimInsetsFrameLayout: FrameLayout {

    interface OnInsetsCallback {
        fun onInsetsChanged(insets: Rect)
    }

    private var insetForeground: Drawable? = null
    private var insets: Rect? = null
    private val tempRect = Rect()
    private var onInsetsCallback: OnInsetsCallback? = null

    constructor(context: Context): super(context) { setUpView(null, 0) }
    constructor(context: Context, attributes: AttributeSet): super(context, attributes) { setUpView(attributes, 0) }
    constructor(context: Context, attributes: AttributeSet, defStyle: Int): super(context, attributes, defStyle) { setUpView(attributes, defStyle) }

    private fun setUpView(attributes: AttributeSet?, defStyle: Int) {

        val typedArray = context.obtainStyledAttributes(attributes,
                R.styleable.ScrimInsetsView, defStyle, 0)

        typedArray?.apply {
            insetForeground = getDrawable(R.styleable.ScrimInsetsView_insetForeground2);
            recycle()
            setWillNotDraw(true)
        }
    }

    override fun fitSystemWindows(insets: Rect): Boolean {

        this.insets = Rect(insets)
        setWillNotDraw(insetForeground == null)
        ViewCompat.postInvalidateOnAnimation(this)
        onInsetsCallback?.onInsetsChanged(insets)

        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (insets != null && insetForeground != null) {
            val insets = this.insets as Rect
            val insetForeground = this.insetForeground as Drawable
            val savedCanvas = canvas.save()
            canvas.translate(scrollX.toFloat(), scrollY.toFloat())

            // Top
            tempRect.set(0, 0, width, insets.top)
            insetForeground.bounds = tempRect
            insetForeground.draw(canvas)

            // Bottom
            tempRect.set(0, height - insets.bottom, width, height)
            insetForeground.bounds = tempRect
            insetForeground.draw(canvas)

            // Left
            tempRect.set(0, insets.top, insets.left, height - insets.bottom)
            insetForeground.bounds = tempRect
            insetForeground.draw(canvas)

            // Right
            tempRect.set(width - insets.right, insets.top, width, height - insets.bottom)
            insetForeground.bounds = tempRect
            insetForeground.draw(canvas)

            canvas.restoreToCount(savedCanvas)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        insetForeground?.callback = this
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        insetForeground?.callback = null
    }

    fun setOnInsetsCallback(onInsetsCallback: OnInsetsCallback) {

        this.onInsetsCallback = onInsetsCallback
    }
}