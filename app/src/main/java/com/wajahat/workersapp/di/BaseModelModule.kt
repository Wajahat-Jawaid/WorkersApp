package com.wajahat.workersapp.di

import com.wajahat.workersapp.ui.BaseModel
import dagger.Module
import dagger.Provides

@Module
class BaseModelModule {

    @AppScope
    @Provides
    fun baseModel(): BaseModel {
        return BaseModel()
    }
}