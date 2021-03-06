package com.example.gb_2_06h_notes.domain;

import java.util.List;

public interface NotesRepository {
    void getNotes(Callback<List<Note>> callback);

    void addNote(String title, String body, String imgeUrl, Callback<Note> callback);

    void remove(Note item, Callback<Object> callback);
}
