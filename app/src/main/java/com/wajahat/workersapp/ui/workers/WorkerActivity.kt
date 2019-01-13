package com.wajahat.workersapp.ui.workers

import android.os.Bundle
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import com.wajahat.workersapp.R
import com.wajahat.workersapp.models.Worker
import com.wajahat.workersapp.ui.BaseActivity
import com.wajahat.workersapp.ui.workers.java.WorkersFragmentRoleBased
import com.wajahat.workersapp.ui.workers.kotlin.WorkersFragmentSimple
import kotlinx.android.synthetic.main.activity_workers.*

class WorkerActivity : BaseActivity(), WorkerContract.View {

    private var dataLoaded = false
    private val presenter = WorkerPresenter(this, WorkerModel())
    private lateinit var mWorkers: ArrayList<Worker>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workers)
        if (hasInternet()) {
            fetchWorkers()
            setSwitchRole()
        }
    }

    private fun setSwitchRole() {
        subscriptions.add(RxCompoundButton.checkedChanges(switch_role)
            .subscribe { checked ->
                if (dataLoaded)
                    if (checked!!) {
                        switchToRoleBasedFragment()
                    } else {
                        switchToRoleLessFragment()
                    }
            }
        )
    }

    private fun fetchWorkers() {
        presenter.getWorkers()
    }

    override fun hideLoader() {
        hideProgress()
    }

    override fun showLoader() {
        showProgress(false)
    }

    override fun showWorkers(workers: ArrayList<Worker>) {
        mWorkers = workers
        dataLoaded = true
        // By Default showing Kotlin fragment which is role less
        switchToRoleLessFragment()
    }

    /** This Fragment which is developed on Kotlin shows Workers without roles section */
    private fun switchToRoleLessFragment() {
        var fg = supportFragmentManager.findFragmentByTag("Kotlin")
        if (fg == null)
            fg = WorkersFragmentSimple.getInstance(mWorkers)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
        transaction
            .addToBackStack(null)
            .replace(R.id.frame_layout, fg)
            .commit()
    }

    /** This Fragment which is developed on Kotlin shows Workers along with the roles section */
    private fun switchToRoleBasedFragment() {
        var fg = supportFragmentManager.findFragmentByTag("Java")
        if (fg == null)
            fg = WorkersFragmentRoleBased.getInstance(mWorkers)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
        transaction
            .addToBackStack(null)
            .replace(R.id.frame_layout, fg)
            .commit()
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    companion object {
        private const val TAG = "AWorkerActivity"
    }
}