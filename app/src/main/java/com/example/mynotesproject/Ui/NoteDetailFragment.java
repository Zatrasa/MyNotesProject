package com.example.mynotesproject.Ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mynotesproject.R;
import com.example.mynotesproject.Data.Note;

public class NoteDetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "note";
    private Note note;

    public static NoteDetailFragment newInstance(Note param1) {
        NoteDetailFragment fragment = new NoteDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView note_date = view.findViewById(R.id.editTextDate);
        TextView note_name = view.findViewById(R.id.editTextName);
        TextView note_text = view.findViewById(R.id.editTextTextMultiLine);

        note_date.setText(note.getN_date());
        note_name.setText(note.getN_name());
        note_text.setText(note.getN_text());

    }
}