package com.wajahat.workersapp.ui

import com.wajahat.workersapp.WorkersApp
import com.wajahat.workersapp.network.ApiInterface
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseModel {

    @Inject
    lateinit var apiInterface: ApiInterface
    val compositeDisposable = CompositeDisposable()

    init {
        WorkersApp.instance?.component?.inject(this)
    }

    fun onDestroy() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }
}