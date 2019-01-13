package com.wajahat.workersapp.ui.workers.kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wajahat.workersapp.Constants
import com.wajahat.workersapp.R
import com.wajahat.workersapp.models.Worker
import kotlinx.android.synthetic.main.workers_fragment_kotlin.*
import java.util.*

/** This associated fragment is the demonstration of Kotlin code. It shows the list
 * of all workers without sections */
class WorkersFragmentSimple : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.workers_fragment_kotlin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Extracting ArrayList of workers from the main activity
        if (arguments != null &&
                arguments?.getParcelableArrayList<Worker>(Constants.WORKERS) != null &&
                arguments!!.getParcelableArrayList<Worker>(Constants.WORKERS).isNotEmpty()) {
            setRecyclerView(arguments!!.getParcelableArrayList<Worker>(Constants.WORKERS))
        }
    }

    private fun setRecyclerView(workers: ArrayList<Worker>) {
        val layoutManager = LinearLayoutManager(context)
        // Adding dividers
        val dividerItemDecoration = DividerItemDecoration(recycler_view.context,
                layoutManager.orientation)
        recycler_view.addItemDecoration(dividerItemDecoration)
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = WorkersAdapterSimple(workers)
    }

    companion object {
        private const val TAG = "WorkersFragmentKotlin"

        fun getInstance(workers: ArrayList<Worker>): WorkersFragmentSimple {
            val fg = WorkersFragmentSimple()
            val bundle = Bundle()
            bundle.putParcelableArrayList(Constants.WORKERS, workers)
            fg.arguments = bundle
            return fg
        }
    }
}