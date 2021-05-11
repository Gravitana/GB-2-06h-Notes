package com.example.gb_2_06h_notes.domain;


import java.util.ArrayList;
import java.util.List;

public class MockNotesRepository implements NotesRepository {
    @Override
    public List<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();

        notes.add(new Note(1, "Первая заметка", "Текст первой заметки.", 1373918302000L));
        notes.add(new Note(2, "Вторая заметка", "Текст второй заметки.", 1245904950000L));
        notes.add(new Note(3, "Заметка №3", "Текст третьей заметки.", 1618185600000L));
        notes.add(new Note(4, "Четвёртая", "Четвёртая заметка с каким-то текстом", 1618581600000L));
        notes.add(new Note(5, "Это пятая", "Пятая", 1051611660000L));
        notes.add(new Note(6, "Шестая", "Это текст шестой заметки", System.currentTimeMillis()));
        notes.add(new Note(7, "№7", "Это седьмая", System.currentTimeMillis()));
        notes.add(new Note(8, "№7", "Это восьмая, но под номером семь", System.currentTimeMillis()));
        notes.add(new Note(9, "Ещё одна", "Очередная заметка без номера. id9", System.currentTimeMillis()));

        return notes;

    }
}
