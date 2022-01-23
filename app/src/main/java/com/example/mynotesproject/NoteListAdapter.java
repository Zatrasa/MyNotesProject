package com.example.mynotesproject;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder>{

    //private NoteListAdapter.OnItemClickListener itemClickListener;
    private OnItemClickListener itemClickListener;
    private int menuPosition;
    private ArrayList<Note> dataSource;

    public NoteListAdapter(ArrayList<Note> notes){
        this.dataSource = notes;
    }

//    public void setDataSource(NoteSource dataSource){
//        this.dataSource = dataSource;
//    }

    @NonNull
    @Override
    public NoteListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new NoteListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListAdapter.ViewHolder holder, int position) {
        holder.bind(dataSource.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void setOnItemClickListener(NoteListAdapter.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView description;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemText);
            description = itemView.findViewById(R.id.itemCommentText);

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null){
                        itemClickListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });

        }
        public void bind(Note note){
//            title.setText(cardData.getTitle());
//            description.setText(cardData.getDescription());
            title.setText(note.getN_name());
            description.setText(note.getN_text());
        }
    }
}
