package com.example.mynotesproject.Data;

public interface DataSourse {
    void init();
    Note getItem(int position);
    int size();

}
