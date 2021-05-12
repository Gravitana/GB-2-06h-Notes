package com.example.gb_2_06h_notes.ui.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gb_2_06h_notes.domain.MockNotesRepository;
import com.example.gb_2_06h_notes.domain.Note;
import com.example.gb_2_06h_notes.domain.NotesRepository;

import java.util.List;

public class NotesListViewModel extends ViewModel {

    private final MutableLiveData<List<Note>> notesLiveData = new MutableLiveData<>();

    private final MutableLiveData<Integer> noteDeletedLiveData = new MutableLiveData<>();

    private final NotesRepository repository = new MockNotesRepository();

    public LiveData<List<Note>> getNotesLiveData() { // убираем Mutable чтобы запретить изменение данных
        return notesLiveData;
    }

    public void requestNotes() {
        List<Note> notes = repository.getNotes();
        notesLiveData.setValue(notes);
    }

    public void deleteClicked(int longClickedPosition) {
        repository.removeAtPosition(longClickedPosition);

        noteDeletedLiveData.setValue(longClickedPosition);
    }

    public LiveData<Integer> getNoteDeletedLiveData() {
        return noteDeletedLiveData;
    }
}
