package com.example.mynotesproject.Ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import android.widget.Toast;

import com.example.mynotesproject.MainActivity;
import com.example.mynotesproject.Navigation;
import com.example.mynotesproject.Data.Note;
import com.example.mynotesproject.R;
import com.google.android.material.snackbar.Snackbar;

public class MenuFragment extends Fragment {

    Navigation navigation;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity)context;
        this.navigation = activity.navigation;
    }

    @Override
    public void onDetach() {
        navigation = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
            navigation.addFragment(new CalendarFragment(),R.id.fragment_Container,true);
            Toast.makeText(requireActivity(),
                    "Calendar",
                    Toast.LENGTH_LONG).show();
        });
    }

    private void initButtonNote(@NonNull View view) {
        Button button_note = view.findViewById(R.id.button_note);
        button_note.setOnClickListener(v->{
            navigation.addFragment(new NotesListFragment(),R.id.fragment_Container,true);
        });
    }
}