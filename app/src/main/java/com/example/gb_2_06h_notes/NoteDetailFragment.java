package com.example.gb_2_06h_notes;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gb_2_06h_notes.domain.Note;

import java.util.Calendar;

public class NoteDetailFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_NOTE = "ARG_NOTE";

    private TextView id, title, body, date;

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

        id = view.findViewById(R.id.note_detail_id);
        title = view.findViewById(R.id.note_detail_title);
        body = view.findViewById(R.id.note_detail_body);
        date = view.findViewById(R.id.note_detail_date);

        Note note = null;
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_NOTE);
        }

        if (note != null) {
            id.setText(String.valueOf(note.getId()));
            title.setText(note.getTitle());
            body.setText(note.getBody());
            date.setText(note.getDate());

            date.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        final Calendar cal = Calendar.getInstance();
        int mYear = cal.get(Calendar.YEAR);
        int mMonth = cal.get(Calendar.MONTH);
        int mDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                (view, year, monthOfYear, dayOfMonth) -> {
                    String editTextDateParam = dayOfMonth + "." + (monthOfYear + 1) + "." + year;
                    date.setText(editTextDateParam);
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}