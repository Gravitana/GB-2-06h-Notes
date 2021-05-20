package com.example.gb_2_06h_notes.ui.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gb_2_06h_notes.domain.Callback;
import com.example.gb_2_06h_notes.domain.FirestoreNotesRepository;
import com.example.gb_2_06h_notes.domain.Note;
import com.example.gb_2_06h_notes.domain.NotesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotesListViewModel extends ViewModel {

    private final MutableLiveData<List<Note>> notesLiveData = new MutableLiveData<>();

    private final NotesRepository repository = new FirestoreNotesRepository();

    public LiveData<List<Note>> getNotesLiveData() { // убираем Mutable чтобы запретить изменение данных
        return notesLiveData;
    }

    public void requestNotes() {
        repository.getNotes(new Callback<List<Note>>() {
            @Override
            public void onSuccess(List<Note> value) {
                notesLiveData.setValue(value);
            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }

    public void addClicked() {
        repository.addNote(
                UUID.randomUUID().toString(),
                "Заметка-заглушка для имитации добавления заметки",
                "https://cdn.pixabay.com/photo/2021/03/17/09/06/snowdrop-6101818_960_720.jpg",
                new Callback<Note>() {
                    @Override
                    public void onSuccess(Note value) {
                        if (notesLiveData.getValue() != null) {
                            ArrayList<Note> notes = new ArrayList<>(notesLiveData.getValue());
                            notes.add(value);
                            notesLiveData.setValue(notes);
                        } else {
                            ArrayList<Note> notes = new ArrayList<>();
                            notes.add(value);
                            notesLiveData.setValue(notes);
                        }
                    }

                    @Override
                    public void onError(Throwable error) {

                    }
                }
        );
    }

    public void deleteClicked(Note note) {
        repository.remove(note, new Callback<Object>() {
            @Override
            public void onSuccess(Object value) {
                if (notesLiveData.getValue() != null) {
                    ArrayList<Note> notes = new ArrayList<>(notesLiveData.getValue());
                    notes.remove(note);
                    notesLiveData.setValue(notes);
                }
            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }
}
