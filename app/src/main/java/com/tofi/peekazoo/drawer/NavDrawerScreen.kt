package com.tofi.peekazoo.drawer

import android.support.annotation.DrawableRes



/**
 * Created by Derek on 18/06/2017.
 * Represents the nav drawer in the app.
 */
interface NavDrawerScreen {

    fun displayBanner(@DrawableRes banner: Int)
    fun addRow(rowText: String, icon: Int?): NavDrawerRowScreen

    fun supplyData(data: Map<String, Int>)
    fun setClickCallback(clickAction: (row: NavDrawerRowScreen) -> Unit)
    fun setSelectedRow(@DrawerDefs.Row drawerItem: String)
}