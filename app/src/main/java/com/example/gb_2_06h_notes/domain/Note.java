package com.example.gb_2_06h_notes.domain;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note implements Parcelable {

    private final int id;
    private final String title;
    private final String body;
    private final Long time;
    private final String imageUrl;

    public Note(int id, String title, String body, Long time, String imageUrl) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.time = time;
        this.imageUrl = imageUrl;
    }

    protected Note(Parcel in) {
        id = in.readInt();
        title = in.readString();
        body = in.readString();
        time = in.readLong();
        imageUrl = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getStringId() {
        return String.valueOf(getId());
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Long getTime() {
        return time;
    }

    public String getDate() {
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date =  new Date(time);
        return dateFormat.format(date);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(body);
        dest.writeLong(time);
        dest.writeString(imageUrl);
    }
}
