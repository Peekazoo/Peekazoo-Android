package com.tofi.peekazoo.drawer

import android.content.Context
import android.support.annotation.DrawableRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.tofi.peekazoo.R
import kotlinx.android.synthetic.main.drawer_row.view.*

/**
 * Created by Derek on 17/06/2017.
 * This custom view represents a row in the nav drawer. Text to display for the row must be supplied. An icon
 * to the left of the text is optional.
 */
class NavDrawerRow: LinearLayout, NavDrawerRowScreen {

    constructor(context: Context): super(context) { setUpView() }
    constructor(context: Context, attributes: AttributeSet): super(context, attributes) { setUpView() }
    constructor(context: Context, attributes: AttributeSet, defStyle: Int): super(context, attributes, defStyle) { setUpView() }

    private lateinit var presenter: NavDrawerRowPresenter
    private var clickAction: ((row: NavDrawerRow) -> Unit)? = null

    /**
     * Implementation from [NavDrawerRowScreen]
     * Displays the row icon to the left of the text
     * @param iconRes The image to use as the icon
     */
    override fun displayRowIcon(@DrawableRes iconRes: Int) {

        drawerIcon.setImageResource(iconRes)
    }

    /**
     * Implementation from [NavDrawerRowScreen]
     * Displays the row text
     * @param text The text to display
     */
    override fun displayRowText(text: String) {

        drawerText.text = text
    }

    /**
     * Implementation from [NavDrawerRowScreen]
     * Use this method to set/unset this row as the current selected row. Any selected states will trigger.
     * @param selectedRow True to set as selected row, false to deselect this row
     */
    override fun setAsSelectedRow(selectedRow: Boolean) {

        isSelected = selectedRow
        drawerIcon.isSelected = selectedRow
        drawerText.isSelected = selectedRow
    }

    /**
     * Implementation from [NavDrawerRowScreen]
     * Set a callback to handle clicks to this row
     */
    override fun setClickCallback(clickAction: (row: NavDrawerRow) -> Unit) {

        this.clickAction = clickAction
    }

    /**
     * Implementation from [NavDrawerRowScreen]
     * @return Module responsible for handling the data
     */
    override fun getDataModule(): NavDrawerRowPresenter = presenter

    /**
     * Implementation from [NavDrawerRowScreen]
     * @return This instance as a view. Needed to allow viewgroups to add this to their hierarchy.
     */
    override fun getAsView(): View = this

    private fun setUpView() {

        LayoutInflater.from(context).inflate(R.layout.drawer_row, this, true)
        orientation = LinearLayout.HORIZONTAL
        minimumHeight = context.resources.getDimension(R.dimen.nav_drawer_row_height).toInt()
        isClickable = true
        setBackgroundResource(R.drawable.statelist_drawer_row)
        setOnClickListener { clickAction?.invoke(this) }

        presenter = NavDrawerRowPresenterImp(this)
    }
}