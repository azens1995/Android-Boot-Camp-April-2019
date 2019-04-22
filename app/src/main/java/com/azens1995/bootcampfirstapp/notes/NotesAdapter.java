package com.azens1995.bootcampfirstapp.notes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azens1995.bootcampfirstapp.AddNotesActivity;
import com.azens1995.bootcampfirstapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Azens Eklak on 19/4/19.
 * Ishani Technology Pvt. Ltd
 * azens1995@gmail.com
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private Context context;
    List<Notes> mAllNotes = Collections.emptyList();

    public NotesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_notes_recyclerview, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Notes notes = mAllNotes.get(i);
        viewHolder.tvNote.setText(notes.getNote());
        viewHolder.tvNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddNotesActivity.class);
                intent.putExtra("NOTES_KEY",notes);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAllNotes.size();
    }

    public void addNotes(List<Notes> notes){
        mAllNotes = notes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNote = itemView.findViewById(R.id.text_row_notes);
        }
    }
}
