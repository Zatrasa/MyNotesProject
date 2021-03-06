package com.example.mynotesproject.Ui;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynotesproject.Data.DataSourse;
import com.example.mynotesproject.Data.NotesList;
import com.example.mynotesproject.MainActivity;
import com.example.mynotesproject.Navigation;
import com.example.mynotesproject.Observe.Observer;
import com.example.mynotesproject.Observe.Publisher;
import com.example.mynotesproject.R;
import com.example.mynotesproject.Data.Note;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class NotesListFragment extends Fragment {

    public DataSourse dataSourse;
    Navigation navigation;
    private Publisher publisher;
    private NoteListAdapter adapter;
    private SharedPreferences sharedPref = null;
    public static final String KEY = "key";

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = requireActivity().getMenuInflater();
        menuInflater.inflate(R.menu.item_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        final int id=item.getItemId();
        switch (id){
            case R.id.action_add:
                addItem();
                return true;
            case R.id.action_update:
                showNote(adapter.getItemSelected());
                return true;
            case R.id.action_delete:
                deleteItem(adapter.getItemSelected());
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void deleteItem(int position){
        dataSourse.deleteItem(position);
        adapter.notifyItemRemoved(position);
    }

    private void addItem(){
        navigation.addFragment(NoteDetailFragment.newInstance(),R.id.fragment_Container,true);
        publisher.subscribe(new Observer() {
            @Override
            public void updateNoteData(Note note) {
                dataSourse.addItem(note);
                adapter.notifyItemInserted(dataSourse.size()-1);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataSourse = new NotesList();
        dataSourse.init();


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        navigation = activity.navigation;
        publisher = activity.publisher;
        sharedPref = activity.getSharedPreferences("My Preferences", MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_notes_list, container, false);
        setHasOptionsMenu(true);


        String savedNotes = sharedPref.getString(KEY, null);
        if (savedNotes == null || savedNotes.isEmpty()) {
            Toast.makeText(requireActivity(), "Empty", Toast.LENGTH_SHORT).show();
        } else {
            try {
                Type type = new TypeToken<NotesList>() {
                }.getType();
                dataSourse = new GsonBuilder().create().fromJson(savedNotes, type);
            } catch (JsonSyntaxException e) {
                Toast.makeText(requireActivity(), "???????????? ??????????????????????????", Toast.LENGTH_SHORT).show();
            }
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_my);
        recyclerView.setHasFixedSize(true);
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(1000);
        animator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(animator);
        adapter = new NoteListAdapter(dataSourse,this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new NoteListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                showNote(position);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void showNote(int position){
        if(Configuration.ORIENTATION_LANDSCAPE==getResources().getConfiguration().orientation){
            showNoteDetailLand(position);
        }
        else{
            showNoteDetail(position);}
    }

    //???????????????????? ???????????????? ?? ???????????????????????? ?????????????? ???????????? ???????????? (?????? ???????????????????? ????????????????????)
    private void showNoteDetail(int position) {
        navigation.addFragment(NoteDetailFragment.newInstance(dataSourse.getItem(position)),R.id.fragment_Container,true);
        Toast.makeText(requireActivity(), dataSourse.getItem(position).getN_name(), Toast.LENGTH_LONG).show();
        publisher.subscribe(new Observer() {
            @Override
            public void updateNoteData(Note note) {
                dataSourse.updateItem(note,position);
                adapter.notifyItemChanged(position);
            }
        });
    }

    //???????????????????? ???????????????? ?? ???????????????????????? ?????????????? ?????????? ???? ?????????????? (?????? ?????????????????????? ????????????????????)
    private void showNoteDetailLand(int position) {
        navigation.addFragment(NoteDetailFragment.newInstance(dataSourse.getItem(position)),R.id.fragment_Container_detail,true);
        Toast.makeText(requireActivity(), dataSourse.getItem(position).getN_name(), Toast.LENGTH_LONG).show();
        publisher.subscribe(new Observer() {
            @Override
            public void updateNoteData(Note note) {
                dataSourse.updateItem(note,position);
                adapter.notifyItemChanged(position);
            }
        });
    }

    @Override
    public void onDetach() {
        navigation = null;
        publisher = null;
        String jsonNotes = new GsonBuilder().create().toJson(dataSourse);
        sharedPref.edit().putString(KEY, jsonNotes).apply();
        super.onDetach();
    }
}


