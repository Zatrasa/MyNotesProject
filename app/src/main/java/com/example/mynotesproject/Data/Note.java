package com.example.mynotesproject.Data;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {

    private String n_name;
    private String n_text;
    private String n_date;


    public Note(String n_name, String n_text, String n_date) {
        this.n_name = n_name;
        this.n_text = n_text;
        this.n_date = n_date;
    }

    public String getN_name() {
        return n_name;
    }

    public String getN_text() {
        return n_text;
    }

    public String getN_date() {
        return n_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.n_name);
        dest.writeString(this.n_text);
        dest.writeString(this.n_date);
    }

    public void readFromParcel(Parcel source) {
        this.n_name = source.readString();
        this.n_text = source.readString();
        this.n_date = source.readString();
    }

    protected Note(Parcel in) {
        this.n_name = in.readString();
        this.n_text = in.readString();
        this.n_date = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

}
