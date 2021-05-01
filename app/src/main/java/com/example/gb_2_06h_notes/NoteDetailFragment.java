package com.example.gb_2_06h_notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gb_2_06h_notes.domain.Note;

public class NoteDetailFragment extends Fragment {

    private static final String ARG_NOTE = "ARG_NOTE";

    public NoteDetailFragment() {
        // Required empty public constructor
    }

    public static NoteDetailFragment newInstance(Note note) {
        NoteDetailFragment fragment = new NoteDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTE, note);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView id = view.findViewById(R.id.note_detail_id);
        TextView title = view.findViewById(R.id.note_detail_title);
        TextView body = view.findViewById(R.id.note_detail_body);

        Note note = getArguments().getParcelable(ARG_NOTE);

        id.setText(String.valueOf(note.getId()));
        title.setText(note.getTitle());
        body.setText(note.getBody());
    }
}