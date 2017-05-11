package com.tofi.peekazoo.di

import javax.inject.Scope
import javax.inject.Singleton

/**
 * Created by Derek on 01/05/2017.
 * Defines scopes used in the application for dependency injection.
 */
@Scope
@Singleton
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope