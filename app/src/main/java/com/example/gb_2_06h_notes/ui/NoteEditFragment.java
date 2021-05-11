package com.example.gb_2_06h_notes.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gb_2_06h_notes.R;
import com.example.gb_2_06h_notes.domain.Note;

public class NoteEditFragment extends Fragment {


    private static final String ARG_NOTE = "ARG_NOTE";

    private TextView id;
    private EditText title, body, date, imageUrl;
    private ImageView image;


    public NoteEditFragment() {
        // Required empty public constructor
    }

    public static NoteEditFragment newInstance(Note note) {
        NoteEditFragment fragment = new NoteEditFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTE, note);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        id = view.findViewById(R.id.note_edit_id);
        title = view.findViewById(R.id.note_edit_title);
        body = view.findViewById(R.id.note_edit_body);
        date = view.findViewById(R.id.note_edit_date);
        imageUrl = view.findViewById(R.id.note_edit_image_url);
        image = view.findViewById(R.id.note_edit_image);

        Note note = null;
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_NOTE);
        }

        if (note != null) {
            id.setText(note.getStringId());
            title.setText(note.getTitle());
            body.setText(note.getBody());
            date.setText(note.getDate());

            Glide.with(image)
                    .load(note.getImageUrl())
                    .centerCrop()
                    .into(image);

//            date.setOnClickListener(this);
        }
    }
}