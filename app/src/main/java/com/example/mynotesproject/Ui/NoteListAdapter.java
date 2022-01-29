package com.example.mynotesproject.Ui;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotesproject.Data.DataSourse;
import com.example.mynotesproject.R;
import com.example.mynotesproject.Data.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder>{

    private OnItemClickListener itemClickListener;
    private int menuPosition;
    private DataSourse dataSource;
    private Fragment fragment;
    private int itemSelected;

    public NoteListAdapter(DataSourse dataSource,Fragment fragment){
        this.dataSource = dataSource;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public NoteListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new NoteListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListAdapter.ViewHolder holder, int position) {
        holder.bind(dataSource.getItem(position));
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
        private TextView date;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemText);
            description = itemView.findViewById(R.id.itemCommentText);
            date = itemView.findViewById(R.id.date);
            fragment.registerForContextMenu(itemView);
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null){
                        itemClickListener.onItemClick(view, getAdapterPosition());
                        itemSelected = getLayoutPosition();
                    }
                }
            });
            title.setOnLongClickListener(new View.OnLongClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public boolean onLongClick(View v) {
                    itemView.showContextMenu(10,10);
                    itemSelected = getLayoutPosition();
                    return true;
                }
            });

        }
        public void bind(Note note){
            title.setText(note.getN_name());
            description.setText(note.getN_text());
            date.setText(new SimpleDateFormat("dd-MM-yy").format(note.getD_date()));
        }
    }

    public int getItemSelected() {
        return itemSelected;
    }
}
