package com.wajahat.workersapp.di

import com.wajahat.workersapp.ui.BasePresenter
import dagger.Module
import dagger.Provides

@Module
class BasePresenterModule {

    @AppScope
    @Provides
    fun basePresenter(): BasePresenter {
        return BasePresenter()
    }
}