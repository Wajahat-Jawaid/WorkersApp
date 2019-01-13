package com.wajahat.workersapp.ui.workers.java;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wajahat.workersapp.Constants;
import com.wajahat.workersapp.R;
import com.wajahat.workersapp.models.Worker;
import com.wajahat.workersapp.ui.workers.WorkerActivity;
import com.wajahat.workersapp.ui.workers.kotlin.WorkersFragmentSimple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This associated fragment is the demonstration of Java code. It shows the list
 * of all workers in sections whereas it's counter part {{@link WorkersFragmentSimple}} has
 * Kotlin and shows all in a single list
 */
public class WorkersFragmentRoleBased extends Fragment {

    private static final String TAG = WorkersFragmentRoleBased.class.getSimpleName();

    /**
     * Create static instance so as to avoid multiple initialisations
     *
     * @param workers Workers list fetched from the server in {@link WorkerActivity}
     */
    public static WorkersFragmentRoleBased getInstance(ArrayList<Worker> workers) {
        WorkersFragmentRoleBased fg = new WorkersFragmentRoleBased();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.WORKERS, workers);
        fg.setArguments(bundle);
        return fg;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.workers_fragment_java, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Extracting ArrayList of workers from the main activity
        if (getArguments() != null
                && getArguments().containsKey(Constants.WORKERS)
                && getArguments().getParcelableArrayList(Constants.WORKERS) != null
                && getArguments().getParcelableArrayList(Constants.WORKERS).size() > 0) {
            setRecyclerView(
                    getArguments().<Worker>getParcelableArrayList(Constants.WORKERS));
        }
    }

    /**
     * Sort the ArrayList fetched from {@link WorkerActivity}
     *
     * @param workers Workers arrayList
     */
    private ArrayList<Worker> getSortedList(@NonNull ArrayList<Worker> workers) {
        // Handling Big-O notation is also required, but that will depend more upon the scenario.
        ArrayList<Worker> sortedWorkers = new ArrayList<>(workers.size());
        sortedWorkers.addAll(workers);
        // In order to create the sections, we first need to sort the collection based on
        // roles
        Collections.sort(sortedWorkers, new Comparator<Worker>() {
            @Override
            public int compare(Worker o1, Worker o2) {
                return o1.getAttributes().getRole().compareTo(o2.getAttributes().getRole());
            }
        });
        // It's obvious that the first item of the recyclerView will be header.
        String roleType = sortedWorkers.get(0).getAttributes().getRole();
        // Adding first header to the list at first position
        sortedWorkers.add(0, new Worker(roleType));
        // Now the list is sorted based on roles, adding role header views.
        for (int i = 1; i < sortedWorkers.size(); i++) {
            // When this condition is true, new role worker is found and hence now there
            // are no more employees with the previous role
            if (sortedWorkers.get(i).getAttributes() != null &&
                    !sortedWorkers.get(i).getAttributes().getRole().equals(roleType)) {
                roleType = sortedWorkers.get(i).getAttributes().getRole();
                sortedWorkers.add(i, new Worker(roleType));
            }
        }

        return sortedWorkers;
    }

    /**
     * @param workers Workers sorted arrayList with role wise sections
     */
    private void setRecyclerView(ArrayList<Worker> workers) {
        assert getView() != null;
        final RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        // Adding dividers
        final DividerItemDecoration itemDecoration = new DividerItemDecoration(
                recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(new WorkersAdapterRoleBased(getSortedList(workers)));
    }
}