package com.wajahat.workersapp

import android.app.Application
import com.wajahat.workersapp.di.AppComponent
import com.wajahat.workersapp.di.ContextModule
import com.wajahat.workersapp.di.DaggerAppComponent

class WorkersApp : Application() {

    var component: AppComponent? = null
    //        private set


    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerAppComponent.builder().contextModule(ContextModule(this)).build()
    }

    companion object {

        var instance: WorkersApp? = null
//            private set
    }
}