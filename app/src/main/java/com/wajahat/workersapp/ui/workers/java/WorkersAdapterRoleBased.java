package com.wajahat.workersapp.ui.workers.java;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wajahat.workersapp.R;
import com.wajahat.workersapp.models.Worker;
import com.wajahat.workersapp.utils.HexValidator;

import java.util.ArrayList;
import java.util.Objects;

public class WorkersAdapterRoleBased extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = WorkersAdapterRoleBased.class.getSimpleName();

    private ArrayList<Worker> mWorkers;

    WorkersAdapterRoleBased(ArrayList<Worker> workers) {
        mWorkers = workers;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == Worker.TYPE_HEADER)
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.ri_worker_header, parent, false));
        else
            return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.ri_worker_role_based, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        if (mWorkers.get(position).getItemType() == Worker.TYPE_HEADER)
            return Worker.TYPE_HEADER;
        else return Worker.TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder)
            ((HeaderViewHolder) holder).bind(mWorkers.get(position));
        else ((ItemViewHolder) holder).bind(mWorkers.get(position));
    }

    @Override
    public int getItemCount() {
        return mWorkers.size();
    }

    /**
     * ViewHolder for Roles header
     */
    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView header;

        HeaderViewHolder(View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.tv_header);
        }

        void bind(Worker worker) {
            header.setText(worker.getRoleType());
        }
    }

    /**
     * ViewHolder for Worker Items
     */
    static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView name, contractor, lastSeen;
        View helmetColor;

        ItemViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            contractor = itemView.findViewById(R.id.tv_contractor);
            lastSeen = itemView.findViewById(R.id.tv_last_seen);
            helmetColor = itemView.findViewById(R.id.v_helmet_color);
        }

        void bind(Worker worker) {
            if (worker.getAttributes() != null) {
                Worker.Attribute attrs = worker.getAttributes();
                name.setText(attrs.getFullName());
                contractor.setText(attrs.getContractor());
                if (attrs.getHelmetColor() != null) {
                    if (HexValidator.validate(attrs.getHelmetColor())) {
                        helmetColor.setBackgroundColor(Objects.requireNonNull(Color.parseColor(
                                HexValidator.getValidHex(attrs.getHelmetColor()))));
                    }
                }
                if (attrs.getInventories() != null && attrs.getInventories().size() > 0
                        && attrs.getInventories().get(0).getInventoryAttribute() != null)
                    lastSeen.setText(Objects.requireNonNull(attrs.
                            getInventories()
                            .get(0)
                            .getInventoryAttribute()
                            .getLastSeen()));
            }
        }
    }
}