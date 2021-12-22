package com.example.mynotesproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    public ArrayList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        notes = new ArrayList<Note>();
        notes.add(new Note("Заметка1","Какая-то заметка под номером 2","01.12.2021"));
        notes.add(new Note("Заметка2","текст заметки","02.12.2021"));
        notes.add(new Note("Заметка3","укпв ва ва ва ав вп в","03.12.2021"));


        MenuFragment menuFragment = new MenuFragment().newInstance(notes);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_Container,menuFragment)
                .commit();
//        NotesListFragment notesListFragment = new NotesListFragment().newInstance(notes);
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment_Container,notesListFragment)
//                .commit();

//        if (Configuration.ORIENTATION_LANDSCAPE==getResources().getConfiguration().orientation){
//            NoteDetailFragment noteDetailFragment = new NoteDetailFragment().newInstance(notes.get(0));
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment_Container_detail,noteDetailFragment)
//                    .commit();
//        }


    }
}