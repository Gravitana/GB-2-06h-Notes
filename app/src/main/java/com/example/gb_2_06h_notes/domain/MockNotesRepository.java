package com.example.gb_2_06h_notes.domain;


import java.util.ArrayList;
import java.util.List;

public class MockNotesRepository implements NotesRepository {
    @Override
    public List<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();

        notes.add(new Note(1, "Первая заметка", "Текст первой заметки.", 1373918302000L, "https://cdn.pixabay.com/photo/2020/12/25/11/57/flamingos-5859192_960_720.jpg"));
        notes.add(new Note(2, "Вторая заметка", "Текст второй заметки.", 1245904950000L, "https://cdn.pixabay.com/photo/2020/05/05/16/54/wisteria-5133949_960_720.jpg"));
        notes.add(new Note(3, "Заметка №3", "Текст третьей заметки.", 1618185600000L, "https://cdn.pixabay.com/photo/2020/04/17/16/48/marguerite-5056063_960_720.jpg"));
        notes.add(new Note(4, "Четвёртая", "Четвёртая заметка с каким-то текстом", 1618581600000L, "https://cdn.pixabay.com/photo/2021/04/17/08/14/woodpecker-6185247_960_720.jpg"));
        notes.add(new Note(5, "Это пятая", "Пятая", 1051611660000L, "https://cdn.pixabay.com/photo/2021/03/17/09/06/snowdrop-6101818_960_720.jpg"));
        notes.add(new Note(6, "Шестая", "Это текст шестой заметки", System.currentTimeMillis(), "https://cdn.pixabay.com/photo/2021/04/18/13/35/flowers-6188414_960_720.jpg"));
        notes.add(new Note(7, "№7", "Это седьмая", System.currentTimeMillis(), "https://cdn.pixabay.com/photo/2021/02/16/18/42/river-6021951_960_720.jpg"));
        notes.add(new Note(8, "№7", "Это восьмая, но под номером семь", System.currentTimeMillis(), "https://cdn.pixabay.com/photo/2020/09/18/20/46/kosmeen-5582938_960_720.jpg"));
        notes.add(new Note(9, "Ещё одна", "Очередная заметка без номера. id9", System.currentTimeMillis(), "https://cdn.pixabay.com/photo/2021/04/25/12/23/flowers-6206279_960_720.jpg"));

        return notes;
    }
}
