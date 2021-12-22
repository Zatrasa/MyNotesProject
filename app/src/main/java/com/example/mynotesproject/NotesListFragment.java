package com.example.mynotesproject;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotesListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotesListFragment extends Fragment {

//    private static final String ARG_PARAM1 = "s_Test";
//    private static final String ARG_PARAM2 = "s_Node";
    private static final String ARG_PARAM3 = "arr_Node";
//    private Note note;
//    private String sTest;
    private ArrayList<Note> notes;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param3 Parameter 1.
     * @return A new instance of fragment NotesListFragment.
     */

    public static NotesListFragment newInstance(ArrayList<Note> param3) {
        NotesListFragment fragment = new NotesListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM3,param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            notes = getArguments().getParcelableArrayList(ARG_PARAM3);
            Log.d("mes","123");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showNotes(view);
    }
    //Создаем список заметок на фрагменте
    private void showNotes(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        for(int i = 0; i<notes.size(); i++){
            TextView tvNoteName = new TextView(getContext());
            tvNoteName.setText(notes.get(i).getN_name());
            tvNoteName.setTextSize(30);
            layoutView.addView(tvNoteName);
            int position = i;
            tvNoteName.setOnClickListener(v -> {
                if(Configuration.ORIENTATION_LANDSCAPE==getResources().getConfiguration().orientation){
                    showNoteDetailLand(notes.get(position));
                }
                else{
                    showNoteDetail(notes.get(position));
                }

            });
        }

    }
    //Показываем фрагмент с детализацией заметки поверх списка (для портретной ориентации)
    private void showNoteDetail(Note note) {
        NoteDetailFragment noteDetailFragment = NoteDetailFragment.newInstance(note);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ((FragmentTransaction) transaction).add(R.id.fragment_Container, noteDetailFragment);
        transaction.addToBackStack("");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }

    //Показываем фрагмент с детализацией заметки рядом со списком (для ландшафтной ориентации)
    private void showNoteDetailLand(Note note) {
        NoteDetailFragment noteDetailFragment = NoteDetailFragment.newInstance(note);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ((FragmentTransaction) transaction).replace(R.id.fragment_Container_detail, noteDetailFragment);
        transaction.addToBackStack("");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }
}


