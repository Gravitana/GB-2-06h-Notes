package com.example.gb_2_06h_notes.ui.edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.gb_2_06h_notes.R;
import com.example.gb_2_06h_notes.domain.Constants;
import com.example.gb_2_06h_notes.domain.Note;

public class EditNoteFragment extends Fragment implements Constants {

    private static final String ARG_NOTE = "ARG_NOTE";

    public EditNoteFragment() {
        // Required empty public constructor
    }

    public static EditNoteFragment newInstance(Note note) {
        EditNoteFragment fragment = new EditNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTE, note);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView id = view.findViewById(R.id.note_edit_id);
        EditText title = view.findViewById(R.id.note_edit_title);
        EditText body = view.findViewById(R.id.note_edit_body);
        EditText date = view.findViewById(R.id.note_edit_date);
        EditText imageUrl = view.findViewById(R.id.note_edit_image_url);
        ImageView image = view.findViewById(R.id.note_edit_image);
    }
}