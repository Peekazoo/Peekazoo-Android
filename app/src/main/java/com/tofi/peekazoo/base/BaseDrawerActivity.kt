package com.tofi.peekazoo.base

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import com.tofi.peekazoo.R
import com.tofi.peekazoo.activities.BaseActivity
import com.tofi.peekazoo.activities.SubmissionsActivity
import com.tofi.peekazoo.drawer.DrawerDefs
import com.tofi.peekazoo.drawer.NavDrawerRowScreen
import com.tofi.peekazoo.drawer.NavDrawerScreen

/**
 * Created by Derek on 18/06/2017.
 * Base activity that includes a nav drawer implementation. The nav drawer is set up in onPostCreate
 * so any interaction with it in a child activity must be done after a super call to that method
 */
abstract class BaseDrawerActivity: BaseActivity() {

    protected abstract var drawerHolder: DrawerLayout
    protected abstract var drawerScreen: NavDrawerScreen

    protected lateinit var drawerToggle: ActionBarDrawerToggle
    protected var selectedDrawerRow: NavDrawerRowScreen? = null
    protected var startDrawerActivity: Boolean = false

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        setUpDrawerToggle()
        setUpDrawerRows()
        drawerHolder.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        drawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    override fun onBackPressed() {

        if (drawerHolder.isDrawerOpen(GravityCompat.START)) {
            drawerHolder.closeDrawer(GravityCompat.START)

        } else {
            supportFinishAfterTransition()
        }
    }

    private fun handleRowSelected(row: NavDrawerRowScreen) {

        selectedDrawerRow = row
        startDrawerActivity = true
        drawerHolder.closeDrawer(GravityCompat.START)
    }

    /**
     * Sets up the drawer toggle
     */
    private fun setUpDrawerToggle() {

        drawerToggle = object : ActionBarDrawerToggle(this, drawerHolder, R.string.drawer_open,
                R.string.drawer_close) {

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)

                // Only start the activity after the drawer has closed for a smoother transition
                if (startDrawerActivity) {

                    startDrawerActivity()
                }
            }
        }

        drawerToggle.isDrawerIndicatorEnabled = true
    }

    private fun setUpDrawerRows() {

        val drawerRows = mutableMapOf<String, Int>()

        drawerRows.apply {
            put(DrawerDefs.SUBMISSIONS, R.drawable.icon_search)
        }

        drawerScreen.getDataModule().supplyData(drawerRows)
        drawerScreen.setClickCallback(this::handleRowSelected)
    }

    private fun startDrawerActivity() {

        var intent: Intent? = null
        @DrawerDefs.Row val drawerItem = selectedDrawerRow?.getDataModule()?.getRowText()

        when (drawerItem) {

            DrawerDefs.SUBMISSIONS -> {
                intent = Intent(this, SubmissionsActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
        }

        if (intent != null) {
            startActivity(intent)
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
            startDrawerActivity = false
        }
    }
}