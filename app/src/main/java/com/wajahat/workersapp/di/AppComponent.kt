package com.wajahat.workersapp.di

import com.wajahat.workersapp.ui.BaseActivity
import com.wajahat.workersapp.ui.BaseModel
import com.wajahat.workersapp.ui.BasePresenter
import dagger.Component

@AppScope
@Component(
        modules = [ContextModule::class, RetrofitModule::class,
            BasePresenterModule::class, BaseModelModule::class]
)
interface AppComponent {

    fun inject(baseActivity: BaseActivity)
    fun inject(baseActivity: BaseModel)
    fun inject(basePresenter: BasePresenter)
}