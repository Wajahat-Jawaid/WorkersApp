package com.wajahat.workersapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import com.wajahat.workersapp.R
import com.wajahat.workersapp.Utils
import com.wajahat.workersapp.WorkersApp
import com.wajahat.workersapp.ui.custom.LoadingDialog
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    private var context: Context? = null
    @Inject
    lateinit var baseModel: BaseModel
    val subscriptions = CompositeDisposable()

    private lateinit var dialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        WorkersApp.instance?.component?.inject(this)
        context = this
        dialog = LoadingDialog(context)
        super.onCreate(savedInstanceState)
    }

    fun hasInternet(): Boolean {
        return if (Utils.hasInternet())
            true
        else {
            showSnack(R.string.no_internet)
            false
        }
    }

    fun showProgress(cancelable: Boolean) {
        dialog.show()
        dialog.setCancelable(cancelable)
    }

    fun hideProgress() {
        dialog.dismiss()
    }

    private fun showSnack(@StringRes msg: Int) {
        val viewGroup = (this
                .findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
        Snackbar.make(viewGroup, msg, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        if (!subscriptions.isDisposed) subscriptions.dispose()
        baseModel.onDestroy()
        super.onDestroy()
    }

    companion object {
        const val TAG = "BaseActivity"
    }
}