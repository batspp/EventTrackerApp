package com.zybooks.battagliaeventtracker;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private final List<Event> eventList;

    public EventAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        // Bind data to views in the ViewHolder
        holder.textViewEventName.setText(event.getName());
        // Set click listeners for edit and delete buttons
        holder.buttonEdit.setOnClickListener(v -> {
            // Handle edit action
        });
        holder.buttonDelete.setOnClickListener(v -> {
            // Handle delete action
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView textViewEventName;
        Button buttonEdit;
        Button buttonDelete;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewEventName = itemView.findViewById(R.id.textViewEventName);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}