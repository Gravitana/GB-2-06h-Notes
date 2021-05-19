package com.example.gb_2_06h_notes.ui.detail;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.gb_2_06h_notes.R;
import com.example.gb_2_06h_notes.domain.Constants;
import com.example.gb_2_06h_notes.domain.Note;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NoteDetailFragment extends Fragment implements Constants { //View.OnClickListener {

    private TextView date;

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
        return inflater.inflate(R.layout.fragment_note_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView id = view.findViewById(R.id.note_detail_id);
        TextView title = view.findViewById(R.id.note_detail_title);
        TextView body = view.findViewById(R.id.note_detail_body);
        date = view.findViewById(R.id.note_detail_date);
        ImageView image = view.findViewById(R.id.note_detail_image);

        Note note = null;
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_NOTE);
        }

        if (note != null) {
            id.setText(note.getId());
            title.setText(note.getTitle());
            body.setText(note.getBody());
            date.setText(DateFormat.format(DATE_FORMAT, note.getCreatedAt()));

            Glide.with(image)
                    .load(note.getImageUrl())
                    .centerCrop()
                    .into(image);

/*
            date.setOnClickListener(this);
*/
        }
    }

/*
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
*/
}