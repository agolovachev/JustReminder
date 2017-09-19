package com.agolovachev.justreminder.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agolovachev.justreminder.R;
import com.agolovachev.justreminder.adapters.CurrentTasksAdapter;
import com.agolovachev.justreminder.model.ModelTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentTaskFragment extends Fragment {

    private RecyclerView rvCurrentTask;
    private RecyclerView.LayoutManager layoutManager;
    private CurrentTasksAdapter adapter;


    public CurrentTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_current_task, container, false);

        rvCurrentTask = (RecyclerView) rootView.findViewById(R.id.rvCurrentTasks);
        layoutManager = new LinearLayoutManager(getActivity());

        rvCurrentTask.setLayoutManager(layoutManager);

        adapter = new CurrentTasksAdapter();
        rvCurrentTask.setAdapter(adapter);

        return rootView;
    }

    public void addTask(ModelTask newTask){
        int position = -1;

        for (int i = 0; i < adapter.getItemCount(); i++){
            if (adapter.getItem(i).isTask()){
                ModelTask task = (ModelTask) adapter.getItem(i);
                if (task.getDate() < newTask.getDate()){
                    position = i;
                    break;
                }
            }
        }
        // Если ни один эелемент из списка не больше нового, добавляем item в локацию position. Новый элемент будет добавляться ниже
        if (position != -1){
            adapter.addItem(position, newTask);
        } else {
            adapter.addItem(newTask);
        }
    }

}
