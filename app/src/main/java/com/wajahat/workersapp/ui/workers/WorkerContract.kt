package com.wajahat.workersapp.ui.workers

import com.wajahat.workersapp.models.Worker
import com.wajahat.workersapp.ui.BasePresenterImpl
import com.wajahat.workersapp.ui.BaseView

interface WorkerContract {

    interface View : BaseView {

        fun showWorkers(workers: ArrayList<Worker>)
    }

    interface OnWorkersFetchedListener : BasePresenterImpl {

        fun onWorkersFetched(workers: ArrayList<Worker>)
    }
}