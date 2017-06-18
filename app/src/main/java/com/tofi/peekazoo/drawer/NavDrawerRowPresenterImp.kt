package com.tofi.peekazoo.drawer

/**
 * Created by Derek on 17/06/2017.
 * This class handles the data for a nav drawer row.
 */
class NavDrawerRowPresenterImp(val screen: NavDrawerRowScreen): NavDrawerRowPresenter {

    @DrawerDefs.Row
    private var drawerText: String? = null

    /**
     * Implementation from [NavDrawerRowPresenter]
     * Sets up the nav drawer row with the data supplied.
     * @param drawerItem The text to display on this nav row. This must not be null/empty.
     * @param iconRes The resource to set as the nav row icon. Pass in null to not display any icon.
     */
    override fun supplyData(drawerItem: String, iconRes: Int?) {

        drawerText = drawerItem
        screen.displayRowText(drawerItem)
        iconRes?.let { screen.displayRowIcon(iconRes) }
    }

    /**
     * Implementation from [NavDrawerRowPresenter]
     * @return The displayed text for this nav row
     */
    override fun getRowText(): String? = drawerText
}