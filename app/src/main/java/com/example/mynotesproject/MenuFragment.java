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
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

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

        initButtonNote(view);
        initButtonCalendar(view);
        initButtonSettings(view);
        initButtonAbout(view);
        initButtonExit(view);
    }

    private void initButtonAbout(@NonNull View view) {
        Button button_about = view.findViewById(R.id.button_about);
        button_about.setOnClickListener(v->{
            Snackbar.make(view, "Under construction", Snackbar.LENGTH_LONG).show();
        });
    }
    private void initButtonSettings(@NonNull View view) {
        Button button_settings = view.findViewById(R.id.button_settings);
        button_settings.setOnClickListener(v->{
            Snackbar.make(view, "Under construction", Snackbar.LENGTH_LONG).show();
        });
    }


    private void initButtonExit(@NonNull View view) {
        Button button_exit = view.findViewById(R.id.button_exit);
        button_exit.setOnClickListener(v->{
            MainActivity ma = (MainActivity)this.getActivity();
            ma.closeProgram();
        });
    }

    private void initButtonCalendar(@NonNull View view) {
        Button button_calenadr = view.findViewById(R.id.button_calendar);
        button_calenadr.setOnClickListener(v->{
            CalendarFragment calendarFragment = new CalendarFragment();
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            ((FragmentTransaction) transaction).add(R.id.fragment_Container, calendarFragment);
            transaction.addToBackStack("");
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
            Toast.makeText(requireActivity(),
                    "Calendar",
                    Toast.LENGTH_LONG).show();
        });
    }

    private void initButtonNote(@NonNull View view) {
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
    }
}