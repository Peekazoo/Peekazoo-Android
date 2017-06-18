package com.tofi.peekazoo.drawer

/**
 * Created by Derek on 17/06/2017.
 * Module that handles the data for a nav drawer row.
 */
interface NavDrawerRowPresenter {

    fun supplyData(@DrawerDefs.Row drawerItem: String, iconRes: Int?)

    @DrawerDefs.Row
    fun getRowText(): String?
}