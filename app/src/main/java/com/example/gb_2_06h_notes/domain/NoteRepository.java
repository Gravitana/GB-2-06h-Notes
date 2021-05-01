package com.example.gb_2_06h_notes.domain;

import java.util.ArrayList;
import java.util.List;

public class NoteRepository {

    public List<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note(1, "Первая заметка", "Текст первой заметки."));
        notes.add(new Note(2, "Вторая заметка", "Текст второй заметки."));
        notes.add(new Note(3, "Заметка №3", "Текст третьей заметки."));
        notes.add(new Note(4, "Четвёртая", "Четвёртая заметка с каким-то текстом"));
        notes.add(new Note(5, "Это пятая", "Пятая"));
        notes.add(new Note(6, "Шестая", "Это текст шестой заметки"));
        notes.add(new Note(7, "№7", "Это седьмая"));
        notes.add(new Note(8, "№7", "Это восьмая, но под номером семь"));
        notes.add(new Note(9, "Ещё одна", "Очередная заметка без номера. id9"));
        return notes;
    }
}
