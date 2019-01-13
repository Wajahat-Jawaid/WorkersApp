package com.wajahat.workersapp.ui.workers

import com.wajahat.workersapp.R
import com.wajahat.workersapp.models.Worker
import com.wajahat.workersapp.ui.BasePresenter

class WorkerPresenter(
        private val view: WorkerContract.View,
        private val model: WorkerModel
) : BasePresenter(), WorkerContract.OnWorkersFetchedListener {

    override fun onWorkersFetched(workers: ArrayList<Worker>) {
        view.showWorkers(workers)
        view.hideLoader()
    }

    fun getWorkers() {
        view.showLoader()
        model.getWorkers(this)
    }

    override fun onSuccess() {
    }

    override fun onFailed() {
        showToast(R.string.workers_failed)
    }

    override fun onApiError() {
        showToast(R.string.req_timeout)
    }

    fun onStop() {
        view.hideLoader()
    }

    companion object {
        private const val TAG = "ALoginPresenter"
    }
}