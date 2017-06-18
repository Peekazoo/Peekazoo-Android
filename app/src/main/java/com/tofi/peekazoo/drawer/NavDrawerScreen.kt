package com.tofi.peekazoo.drawer

import android.support.annotation.DrawableRes



/**
 * Created by Derek on 18/06/2017.
 * Represents the nav drawer in the app.
 */
interface NavDrawerScreen {

    fun displayBanner(@DrawableRes banner: Int)
    fun addRow(rowText: String, icon: Int?): NavDrawerRowScreen

    fun setClickCallback(clickAction: (row: NavDrawerRowScreen) -> Unit)
    fun getDataModule(): NavDrawerPresenter
}