package com.wajahat.workersapp.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule (private val context: Context) {

    @AppScope
    @Provides
    fun context() : Context {
        return context
    }
}