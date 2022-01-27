package com.example.mynotesproject.Data;

import java.util.ArrayList;

public class NotesList implements DataSourse {
    public ArrayList<Note> notes;

    public NotesList() {
        this.notes = new ArrayList<Note>();
    }

    @Override
    public void init() {
        notes.add(new Note("Заметка1","Какая-то заметка под номером 2","01.12.2021"));
        notes.add(new Note("Заметка2","текст заметки","02.12.2021"));
        notes.add(new Note("Заметка3","укпв ва ва ва ав вп в","03.12.2021"));
    }

    @Override
    public Note getItem(int position) {
        return notes.get(position);
    }

    @Override
    public int size() {
        return notes.size();
    }
}
