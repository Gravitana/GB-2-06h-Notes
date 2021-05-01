package com.example.gb_2_06h_notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gb_2_06h_notes.domain.Note;
import com.example.gb_2_06h_notes.domain.NoteRepository;

import java.util.List;

public class NotesListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Note> notes = new NoteRepository().getNotes();

        LinearLayout notesList = view.findViewById(R.id.notes_list);

        for (Note note : notes) {
            View noteView = LayoutInflater.from(requireContext())
                    .inflate(R.layout.note_item, notesList, false);

            TextView id = noteView.findViewById(R.id.note_item_id);
            TextView title = noteView.findViewById(R.id.note_item_title);

            id.setText(String.valueOf(note.getId()));
            title.setText(note.getTitle());

            notesList.addView(noteView);
        }
    }
}