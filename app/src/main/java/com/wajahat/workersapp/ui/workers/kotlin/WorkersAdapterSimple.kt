package com.wajahat.workersapp.ui.workers.kotlin

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wajahat.workersapp.R
import com.wajahat.workersapp.models.Worker
import com.wajahat.workersapp.utils.HexValidator
import kotlinx.android.synthetic.main.ri_worker_simple.view.*
import java.util.*

class WorkersAdapterSimple(private val mWorkers: List<Worker>) :
        RecyclerView.Adapter<WorkersAdapterSimple.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.ri_worker_simple))
    }

    override fun getItemCount(): Int {
        return mWorkers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mWorkers[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(worker: Worker) {
            val attr = worker.attributes
            if (attr != null) {
                itemView.tv_name.text = attr.fullName
                itemView.tv_role.text = attr.role
                itemView.tv_contractor.text = attr.contractor
                // Helmet color
                if (attr.helmetColor != null) {
                    val helmetColor = attr.helmetColor
                    if (HexValidator.validate(helmetColor!!)) {
                        itemView.v_helmet_color
                                .setBackgroundColor(Objects
                                        .requireNonNull(Color
                                                .parseColor(HexValidator
                                                        .getValidHex(helmetColor))))
                    }
                }
                if (attr.inventories != null && attr.inventories!!.isNotEmpty())
                    itemView.tv_last_seen.text =
                            Objects.requireNonNull(attr.inventories!![0].inventoryAttribute?.lastSeen)
            }
        }
    }

    /** Extension method of Kotlin */
    private fun ViewGroup.inflate(resId: Int): View {
        return LayoutInflater.from(context).inflate(resId, this, false)
    }

    companion object {
        private val TAG = "WorkersAdapterSimple"
    }
}