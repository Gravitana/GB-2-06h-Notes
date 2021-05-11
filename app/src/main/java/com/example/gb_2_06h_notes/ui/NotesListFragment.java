package com.example.gb_2_06h_notes.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gb_2_06h_notes.R;
import com.example.gb_2_06h_notes.domain.Note;
import com.example.gb_2_06h_notes.domain.MockNotesRepository;

import java.util.List;

public class NotesListFragment extends Fragment {

    public interface NoteClickListener {
        void onNoteClicked(Note note);
    }

    private NoteClickListener noteClickListener;

    public NotesListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof NoteClickListener) {
            noteClickListener = (NoteClickListener) context;
        }
    }

    @Override
    public void onDetach() {
        noteClickListener = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Note> notes = new MockNotesRepository().getNotes();

        RecyclerView notesList = view.findViewById(R.id.notes_list);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        notesList.setLayoutManager(lm);

        NotesAtapter adapter = new NotesAtapter();

        notesList.setAdapter(adapter);

        adapter.addData(notes);

        adapter.setOnNotesListItemClickListener(new NotesAtapter.OnNotesListItemClickListener() {
            @Override
            public void onNotesListItemClick(View view, int position) {
                openNoteDetail(notes.get(position));
            }
        });

        adapter.notifyDataSetChanged(); // перерисовка списка

/*
        LinearLayout notesList = view.findViewById(R.id.notes_list);

        for (Note note : notes) {
            View noteView = LayoutInflater.from(requireContext())
                    .inflate(R.layout.note_item, notesList, false);

            noteView.setOnClickListener(v -> openNoteDetail(note));

            TextView id = noteView.findViewById(R.id.note_item_id);
            TextView title = noteView.findViewById(R.id.note_item_title);

            id.setText(String.valueOf(note.getId()));
            title.setText(note.getTitle());

            notesList.addView(noteView);
        }
*/
    }

    private void openNoteDetail(Note note) {
        if (noteClickListener != null) {
            noteClickListener.onNoteClicked(note);
        }
    }
}