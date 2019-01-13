package com.wajahat.workersapp.ui

/** Base Interface implemented by the Activities i.e. MVP Views */
interface BaseView {

    /** Show progress bar */
    fun showLoader()

    /** Hide progress bar */
    fun hideLoader()
}