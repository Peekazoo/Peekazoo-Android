package com.tofi.peekazoo.di.components

import com.tofi.peekazoo.MainActivity
import com.tofi.peekazoo.di.ActivityScope
import dagger.Subcomponent

/**
 * Created by Derek on 01/05/2017.
 * Dependency injection component for providing application level modules
 */
@ActivityScope
@Subcomponent
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}