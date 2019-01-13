package com.wajahat.workersapp.ui

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast
import com.wajahat.workersapp.WorkersApp
import javax.inject.Inject

open class BasePresenter {

    @Inject
    lateinit var context: Context

    init {
        WorkersApp.instance?.component?.inject(this)
    }

    fun showToast(@StringRes msg: Int) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}