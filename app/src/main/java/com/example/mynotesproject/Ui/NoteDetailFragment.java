package com.example.mynotesproject.Ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.mynotesproject.MainActivity;
import com.example.mynotesproject.Observe.Publisher;
import com.example.mynotesproject.R;
import com.example.mynotesproject.Data.Note;

import java.util.Calendar;
import java.util.Date;

public class NoteDetailFragment extends Fragment {

    private static final String ARG_NOTE = "note";
    private Note note;
    private Publisher publisher;
    DatePicker note_date;
    TextView note_name;
    TextView note_text;

    public static NoteDetailFragment newInstance(Note paramNote) {
        NoteDetailFragment fragment = new NoteDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, paramNote);
        fragment.setArguments(args);
        return fragment;
    }

    public static NoteDetailFragment newInstance() {
        NoteDetailFragment fragment = new NoteDetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_NOTE);
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
        //note_date = view.findViewById(R.id.editTextDate);
        note_date = view.findViewById(R.id.inputDate);
        note_name = view.findViewById(R.id.editTextName);
        note_text = view.findViewById(R.id.editTextTextMultiLine);

        if (note!=null){
            initDatePicker(note.getD_date());
            note_name.setText(note.getN_name());
            note_text.setText(note.getN_text());
        }
    }

    private void initDatePicker(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.note_date.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                null);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        publisher = activity.publisher;
    }

    @Override
    public void onDetach() {
        publisher = null;
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        publisher.notifySingle(SaveChanges());
    }

    public Note SaveChanges(){
        if (note!=null){
            note.setD_date(getDateFromDatePicker());
            note.setN_text(note_text.getText().toString());
            note.setN_name(note_name.getText().toString());
        }
        else{
            note = new Note(note_name.getText().toString(),note_text.getText().toString(),getDateFromDatePicker());
        }
        return note;
    }

    private Date getDateFromDatePicker() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, this.note_date.getYear());
        cal.set(Calendar.MONTH, this.note_date.getMonth());
        cal.set(Calendar.DAY_OF_MONTH, this.note_date.getDayOfMonth());
        return cal.getTime();
    }
}