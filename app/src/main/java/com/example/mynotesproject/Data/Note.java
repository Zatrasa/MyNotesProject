package com.example.mynotesproject.Data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Note implements Parcelable {

    private String n_name;
    private String n_text;
    private Date d_date;


    public Note(String n_name, String n_text, Date d_date) {
        this.n_name = n_name;
        this.n_text = n_text;
        this.d_date = d_date;
    }

    public String getN_name() {
        return n_name;
    }

    public String getN_text() {
        return n_text;
    }

    public Date getD_date(){ return  d_date;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.n_name);
        dest.writeString(this.n_text);
        dest.writeLong(d_date.getTime());
    }

    protected Note(Parcel in) {
        this.n_name = in.readString();
        this.n_text = in.readString();
        this.d_date = new Date(in.readLong());
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

    public void setN_name(String n_name) {
        this.n_name = n_name;
    }

    public void setN_text(String n_text) {
        this.n_text = n_text;
    }

    public void setD_date(Date d_date) {
        this.d_date = d_date;
    }
}
