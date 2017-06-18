package com.tofi.peekazoo.drawer

/**
 * Created by Derek on 18/06/2017.
 * This class handles the data logic for the nav drawer. Responsibly for setting up the nav drawer rows and
 * for keeping track of the currently selected row.
 */
class NavDrawerPresenterImp(val screen: NavDrawerScreen,
                            val clickListener: (row: NavDrawerRowScreen) -> Unit): NavDrawerPresenter {

    private val drawerRows: MutableList<NavDrawerRowScreen> = mutableListOf()
    private var selectedDrawerRow: NavDrawerRowScreen? = null

    /**
     * Implementation from [NavDrawerPresenter]
     * Used to set a row as the currently selected row in the nav drawer. The previously selected row gets
     * deselected. Any selection/deselection states for the row UI elements will trigger.
     * @param selectedRow The row to set as the selected one
     */
    override fun handleRowSelection(selectedRow: NavDrawerRowScreen) {

        if (selectedDrawerRow != selectedRow) {

            selectedDrawerRow?.setAsSelectedRow(false)
            selectedRow.setAsSelectedRow(true)
            selectedDrawerRow = selectedRow
            clickListener(selectedRow)
        }
    }

    /**
     * Implementation from [NavDrawerPresenter]
     * Set a drawer row as the selected one.
     * @param drawerItem The text shown for the drawer row
     */
    override fun setSelectedRow(@DrawerDefs.Row drawerItem: String) {

        drawerRows.forEach {

            if (it.getDataModule().getRowText() == drawerItem) {

                handleRowSelection(it)
                return
            }
        }
    }

    /**
     * Implementation from [NavDrawerPresenter]
     * Reset the state of the selected row
     */
    override fun resetSelectedRow() {

        if (selectedDrawerRow !== null) {

            selectedDrawerRow?.setAsSelectedRow(false)
            selectedDrawerRow = null
        }
    }

    /**
     * Implementation from [NavDrawerPresenter]
     * Sets up all the nav drawer rows with the data provided.
     * @param data Map containing data for constructing the rows. The keys are strings representing the name
     *                of the row to display. The values are integers representing the resource ID of the
     *                drawable to display as the row icon. Supply a drawable statelist if you want the icon
     *                to have different selector states.
     */
    override fun supplyData(data: Map<String, Int>) {

        data.forEach { text, iconRes ->

            drawerRows.add(screen.addRow(text, iconRes))
        }
    }
}