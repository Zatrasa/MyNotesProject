package com.example.mynotesproject.Data;

import java.util.ArrayList;
import java.util.Calendar;

public class NotesList implements DataSourse {
    public ArrayList<Note> notes;

    public NotesList() {
        this.notes = new ArrayList<Note>();
    }

    @Override
    public void init() {
        notes.add(new Note("Заметка1","Какая-то заметка под номером 2", Calendar.getInstance().getTime()));
        notes.add(new Note("Заметка2","текст заметки",Calendar.getInstance().getTime()));
        notes.add(new Note("Заметка3","укпв ва ва ва ав вп в",Calendar.getInstance().getTime()));
    }

    @Override
    public Note getItem(int position) {
        return notes.get(position);
    }

    @Override
    public int size() {
        return notes.size();
    }

    @Override
    public void updateItem(Note note, int position) {
        notes.set(position,note);
    }

    @Override
    public void addItem(Note note) {   notes.add(note);   }

    @Override
    public void deleteItem(int position) {notes.remove(position);}
}
