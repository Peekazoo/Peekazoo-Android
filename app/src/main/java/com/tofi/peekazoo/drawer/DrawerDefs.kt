package com.tofi.peekazoo.drawer

import android.support.annotation.StringDef

/**
 * Created by Derek on 17/06/2017.
 * String defs for the nav drawer items in the app.
 */
object DrawerDefs {

    @StringDef(SUBMISSIONS)
    annotation class Row

    const val SUBMISSIONS = "Submissions"
}