package com.wajahat.workersapp.ui.workers

import com.wajahat.workersapp.api.GetWorkersResponse
import com.wajahat.workersapp.ui.BaseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WorkerModel : BaseModel() {

    fun getWorkers(listener: WorkerContract.OnWorkersFetchedListener) {
        val subscription = apiInterface.getWorkers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ resp: GetWorkersResponse? ->
                    if (resp?.data != null && resp.data?.workers != null
                            && resp.data?.workers!!.isNotEmpty()) {
                        listener.onWorkersFetched(resp.data?.workers!!)
                    } else listener.onFailed()
                }, {
                    listener.onApiError()
                })

        compositeDisposable.add(subscription)
    }

    companion object {
        private const val TAG = "AWorkerModel"
    }
}