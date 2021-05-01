package com.example.gb_2_06h_notes.domain;

public class Note {

    private int id;
    private String title;
    private String body;

    public Note(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
