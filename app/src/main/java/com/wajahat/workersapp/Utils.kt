package com.wajahat.workersapp

import android.content.Context
import android.net.ConnectivityManager

class Utils {

    companion object {

        fun hasInternet(): Boolean {
            val cm =
                    WorkersApp.instance
                            ?.applicationContext
                            ?.getSystemService(Context.CONNECTIVITY_SERVICE)
                            as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}