package com.wajahat.workersapp.ui

/** MVP Presenters base class */
interface BasePresenterImpl {

    /** These 3 presenter life-cycle methods are mainly pertained to the data loading
     * operations from the remote server */
    fun onSuccess()

    fun onFailed()
    fun onApiError()
}