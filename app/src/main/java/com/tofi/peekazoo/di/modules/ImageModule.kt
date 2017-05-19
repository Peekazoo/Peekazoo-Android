package com.tofi.peekazoo.di.modules

import android.content.Context
import com.squareup.picasso.Picasso
import com.tofi.peekazoo.BuildConfig
import com.tofi.peekazoo.di.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * Created by Derek on 18/05/2017.
 * Provides dependencies for loading images in the app
 */
@Module
class ImageModule(val context: Context) {

    @Provides @ApplicationScope
    fun providePicasso(): Picasso {
        return Picasso.Builder(context)
                .loggingEnabled(BuildConfig.DEBUG)
                .build()
    }
}