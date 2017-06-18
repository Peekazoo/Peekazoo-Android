package com.tofi.peekazoo.drawer

import android.content.Context
import android.graphics.Color
import android.support.annotation.DrawableRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ScrollView
import com.tofi.peekazoo.R
import kotlinx.android.synthetic.main.drawer_main.view.*

/**
 * Created by Derek on 18/06/2017.
 * This custom view represents the nav drawer used throughout the app.
 */
class NavDrawer: ScrollView, NavDrawerScreen {

    private lateinit var presenter: NavDrawerPresenter
    private var clickAction: ((row: NavDrawerRowScreen) -> Unit)? = null

    constructor(context: Context): super(context) { setUpView() }
    constructor(context: Context, attributes: AttributeSet): super(context, attributes) { setUpView() }
    constructor(context: Context, attributes: AttributeSet, defStyle: Int): super(context, attributes, defStyle) { setUpView() }

    /**
     * Implementation from [NavDrawerScreen]
     * Displays a banner in the upper part of the nav drawer.
     * @param banner The image to set as the banner
     */
    override fun displayBanner(@DrawableRes banner: Int) {

        drawerBanner.setImageResource(banner)
    }

    /**
     * Implementation from [NavDrawerScreen]
     * Add another row to the nav drawer.
     * @param rowText The text to display on the row
     * @param icon The icon to use for the row that appears to the left of the text
     * @return The newly instantiated nav drawer row
     */
    override fun addRow(rowText: String, icon: Int?): NavDrawerRowScreen {

        val row = NavDrawerRow(context)
        row.getDataModule().supplyData(rowText, icon)
        row.setClickCallback { clickAction!! }
        drawerHolder.addView(row.getAsView())
        return row
    }

    /**
     * Implementation from [NavDrawerScreen]
     * Set a callback to handle clicks to rows
     */
    override fun setClickCallback(clickAction: (row: NavDrawerRowScreen) -> Unit) {

        this.clickAction = clickAction
    }

    /**
     * Implementation from [NavDrawerScreen]
     * @return The module responsible for handling the data
     */
    override fun getDataModule(): NavDrawerPresenter = presenter

    private fun setUpView() {

        LayoutInflater.from(context).inflate(R.layout.drawer_main, this, true)
        setBackgroundColor(Color.WHITE)

        presenter = NavDrawerPresenterImp(this, this::onRowClicked)
    }

    private fun onRowClicked(row: NavDrawerRowScreen) {

        presenter.handleRowSelection(row)
        clickAction?.invoke(row)
    }
}