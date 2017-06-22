package com.tofi.peekazoo.drawer

import android.support.annotation.DrawableRes
import android.view.View

/**
 * Created by Derek on 17/06/2017.
 * Represents a nav drawer row
 */
interface NavDrawerRowScreen {

    fun displayRowIcon(@DrawableRes iconRes: Int)
    fun displayRowText(text: String)

    fun supplyData(@DrawerDefs.Row drawerItem: String, iconRes: Int?)
    fun setAsSelectedRow(selectedRow: Boolean)
    fun setClickCallback(clickAction: (row: NavDrawerRow) -> Unit)
    @DrawerDefs.Row fun getRowType(): String?
    fun getAsView(): View
}