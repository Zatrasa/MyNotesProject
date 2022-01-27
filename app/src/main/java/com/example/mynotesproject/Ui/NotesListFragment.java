package com.example.mynotesproject.Ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynotesproject.Data.DataSourse;
import com.example.mynotesproject.Data.NotesList;
import com.example.mynotesproject.MainActivity;
import com.example.mynotesproject.Navigation;
import com.example.mynotesproject.R;
import com.example.mynotesproject.Data.Note;

import java.util.ArrayList;

public class NotesListFragment extends Fragment {

    private static final String ARG_PARAM3 = "arr_Node";
    public DataSourse dataSourse;
    Navigation navigation;

//    public static NotesListFragment newInstance(ArrayList<Note> param3) {
//        NotesListFragment fragment = new NotesListFragment();
//        Bundle args = new Bundle();
//        args.putParcelableArrayList(ARG_PARAM3,param3);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataSourse = new NotesList();
        dataSourse.init();
//        if (getArguments() != null) {
//            notes = getArguments().getParcelableArrayList(ARG_PARAM3);
//        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        navigation = activity.navigation;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_notes_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_my);
        recyclerView.setHasFixedSize(true);
        NoteListAdapter adapter = new NoteListAdapter(dataSourse);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new NoteListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if(Configuration.ORIENTATION_LANDSCAPE==getResources().getConfiguration().orientation){
                    showNoteDetailLand(dataSourse.getItem(position));
                }
                else{
                    showNoteDetail(dataSourse.getItem(position));}
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    //Показываем фрагмент с детализацией заметки поверх списка (для портретной ориентации)
    private void showNoteDetail(Note note) {
        navigation.addFragment(NoteDetailFragment.newInstance(note),R.id.fragment_Container,true);
        Toast.makeText(requireActivity(), note.getN_name(), Toast.LENGTH_LONG).show();
    }

    //Показываем фрагмент с детализацией заметки рядом со списком (для ландшафтной ориентации)
    private void showNoteDetailLand(Note note) {
        navigation.addFragment(NoteDetailFragment.newInstance(note),R.id.fragment_Container_detail,true);
        Toast.makeText(requireActivity(), note.getN_name(), Toast.LENGTH_LONG).show();
    }
}


