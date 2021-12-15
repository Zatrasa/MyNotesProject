package com.example.mynotesproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoteDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "note";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
    private Note note;
//    private String mParam2;

    public NoteDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment NoteDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
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