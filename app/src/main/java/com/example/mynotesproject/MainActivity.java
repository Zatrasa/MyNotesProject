package com.example.mynotesproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    public ArrayList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        initDrawer(toolbar);


        notes = new ArrayList<Note>();
        notes.add(new Note("Заметка1","Какая-то заметка под номером 2","01.12.2021"));
        notes.add(new Note("Заметка2","текст заметки","02.12.2021"));
        notes.add(new Note("Заметка3","укпв ва ва ва ав вп в","03.12.2021"));

        MenuFragment menuFragment = new MenuFragment().newInstance(notes);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_Container,menuFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_note:
                showNotes();
                return true;
            case R.id.menu_calendar:
                showCalendar();
                return true;
            case R.id.menu_close:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showNotes() {
        NotesListFragment notesListFragment = NotesListFragment.newInstance(notes);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ((FragmentTransaction) transaction).add(R.id.fragment_Container, notesListFragment);
        transaction.addToBackStack("");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }
    private void showCalendar(){
        CalendarFragment calendarFragment = new CalendarFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ((FragmentTransaction) transaction).add(R.id.fragment_Container, calendarFragment);
        transaction.addToBackStack("");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }

    private void initDrawer(Toolbar toolbar) {
        // Находим DrawerLayout
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        // Создаем ActionBarDrawerToggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Обработка навигационного меню
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.menu_note:
                        showNotes();
                        drawer.closeDrawers();
                        return true;
                    case R.id.menu_calendar:
                        showCalendar();
                        drawer.closeDrawers();
                        return true;
                    case R.id.menu_close:
                        finish();
                        return true;
                }
                return false;
            }
        });
    }


}