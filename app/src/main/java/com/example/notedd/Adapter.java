package com.example.notedd;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{


    Context context;
    Activity activity;
    List<Model> notesList;


    public Adapter(Context context, Activity activity, List<Model> notesList) {
        this.context = context;
        this.activity = activity;
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout,parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(notesList.get(position).getTitle());
        holder.subtitle.setText(notesList.get(position).getSubtitle());
        holder.note.setText(notesList.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, subtitle, note;
        RelativeLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
            note = itemView.findViewById(R.id.note);
            layout = itemView.findViewById(R.id.note_layout);


        }


    }
}
