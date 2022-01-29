package com.example.mynotesproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mynotesproject.Data.DataSourse;
import com.example.mynotesproject.Data.Note;
import com.example.mynotesproject.Data.NotesList;
import com.example.mynotesproject.Observe.Publisher;
import com.example.mynotesproject.Ui.CalendarFragment;
import com.example.mynotesproject.Ui.MenuFragment;
import com.example.mynotesproject.Ui.NotesListFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public Navigation navigation;
    public Publisher publisher = new Publisher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigation = new Navigation(getSupportFragmentManager());
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        initDrawer(toolbar);
        navigation.addFragment(new MenuFragment(),R.id.fragment_Container,false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        MenuSelect(item);
        return super.onOptionsItemSelected(item);
    }

    public void closeProgram(){
        new AlertDialog.Builder(this)
                .setTitle("Program close!")
                .setMessage("Close program?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Closed", Toast.LENGTH_LONG).show();
                        finish();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }

    private void showNotes() {
        navigation.addFragment(new NotesListFragment(),R.id.fragment_Container,true);
        Toast.makeText(this, R.string.txt_note, Toast.LENGTH_LONG).show();
    }
    private void showCalendar(){
        navigation.addFragment(new CalendarFragment(),R.id.fragment_Container,true);
        Toast.makeText(this, R.string.txt_calendar, Toast.LENGTH_LONG).show();
    }

    private void initDrawer(Toolbar toolbar) {
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
                drawer.closeDrawers();
                return MenuSelect(item);
            }
        });
    }

    private boolean MenuSelect(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_note:
                showNotes();
                return true;
            case R.id.menu_calendar:
                showCalendar();
                return true;
            case R.id.menu_close:
                closeProgram();
                return true;
        }
        return false;
    }


}