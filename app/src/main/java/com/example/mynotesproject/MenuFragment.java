package com.example.mynotesproject;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuFragment extends Fragment {


    //    private static final String ARG_PARAM1 = "s_Test";
//    private static final String ARG_PARAM2 = "s_Node";
    private static final String ARG_PARAM3 = "arr_Node";
    //    private Note note;
//    private String sTest;
    private ArrayList<Note> m_notes;

    public static MenuFragment newInstance(ArrayList<Note> param3) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM3,param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            m_notes = getArguments().getParcelableArrayList(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button_note = view.findViewById(R.id.button_note);
        button_note.setOnClickListener(v->{
            NotesListFragment notesListFragment = NotesListFragment.newInstance(m_notes);
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            ((FragmentTransaction) transaction).add(R.id.fragment_Container, notesListFragment);
            transaction.addToBackStack("");
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        });
        Button button_calenadr = view.findViewById(R.id.button_calendar);
        button_calenadr.setOnClickListener(v->{
            CalendarFragment calendarFragment = new CalendarFragment();
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            ((FragmentTransaction) transaction).add(R.id.fragment_Container, calendarFragment);
            transaction.addToBackStack("");
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        });
    }
}