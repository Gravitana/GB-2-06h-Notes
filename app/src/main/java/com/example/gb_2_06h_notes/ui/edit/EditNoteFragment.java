package com.example.gb_2_06h_notes.ui.edit;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gb_2_06h_notes.R;
import com.example.gb_2_06h_notes.domain.Constants;
import com.example.gb_2_06h_notes.domain.Note;

import java.util.Calendar;

public class EditNoteFragment extends Fragment implements Constants {

    private Note note;

    public EditNoteFragment() {
        // Required empty public constructor
    }

    public static EditNoteFragment newInstance(Note note) {
        EditNoteFragment fragment = new EditNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(CURRENT_NOTE, note);
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

        if (getArguments() != null)
            note = getArguments().getParcelable(CURRENT_NOTE);

        TextView id = view.findViewById(R.id.note_edit_id);
        if (note != null)
            id.setText("id: " + note.getId());

        EditText editTitle = view.findViewById(R.id.note_edit_title);
        if (note != null)
            editTitle.setText(note.getTitle());

        EditText editBody = view.findViewById(R.id.note_edit_body);
        if (note != null)
            editBody.setText(note.getBody());

        EditText editImageUrl = view.findViewById(R.id.note_edit_image_url);
        if (note != null)
            editImageUrl.setText(note.getImageUrl());

        Button editDate = view.findViewById(R.id.note_edit_date);
        if (note != null)
            editDate.setText(DateFormat.format(DATE_FORMAT, note.getCreatedAt()));
        else
            editDate.setText(R.string.set_date);

        editDate.setOnClickListener(v -> {
            final Calendar cal = Calendar.getInstance();
            int mYear = cal.get(Calendar.YEAR);
            int mMonth = cal.get(Calendar.MONTH);
            int mDay = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    (view1, year, monthOfYear, dayOfMonth) -> {
                        String editTextDateParam = dayOfMonth + "." + (monthOfYear + 1) + "." + year;
                        editDate.setText(editTextDateParam);
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        });

/*
        DatePicker picker = view.findViewById(R.id.date_picker);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            picker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Toast.makeText(requireContext(), dayOfMonth + "." + monthOfYear+ "." + year, Toast.LENGTH_SHORT).show();
                }
            });
        }
*/
    }
}