package com.example.gb_2_06h_notes.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Objects;

public class Note implements Parcelable {

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

    private final String id;
    private final String title;
    private final String body;
    private final Date createdAt;
    private final String imageUrl;

    public Note(String id, String title, String body, Date createdAt, String imageUrl) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
    }

    protected Note(Parcel in) {
        id = in.readString();
        title = in.readString();
        body = in.readString();
        createdAt = (Date) in.readSerializable();
        imageUrl = in.readString();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Date getCreatedAt() {
        return createdAt;
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
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(body);
        dest.writeSerializable(createdAt);
        dest.writeString(imageUrl);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id &&
                Objects.equals(title, note.title) &&
                Objects.equals(body, note.body) &&
                Objects.equals(createdAt, note.createdAt) &&
                Objects.equals(imageUrl, note.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, body, createdAt, imageUrl);
    }
}
