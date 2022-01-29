package com.example.mynotesproject.Data;

public interface DataSourse {
    void init();
    Note getItem(int position);
    void updateItem(Note note,int position);
    void addItem(Note note);
    void deleteItem(int position);
    int size();

}
