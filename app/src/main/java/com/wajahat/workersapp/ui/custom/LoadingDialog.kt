package com.wajahat.workersapp.ui.custom

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.wajahat.workersapp.R

class LoadingDialog(context: Context?) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_dialog)
    }
}