package com.tofi.peekazoo.drawer

/**
 * Created by Derek on 18/06/2017.
 * Handles the data for a nav drawer row.
 */
interface NavDrawerPresenter {

    fun handleRowSelection(selectedRow: NavDrawerRowScreen)
    fun setSelectedRow(@DrawerDefs.Row drawerItem: String)
    fun resetSelectedRow()

    fun supplyData(data: Map<String, Int>)
}