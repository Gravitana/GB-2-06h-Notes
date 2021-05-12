package com.example.gb_2_06h_notes.ui.list;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gb_2_06h_notes.R;
import com.example.gb_2_06h_notes.domain.Note;
import com.example.gb_2_06h_notes.domain.MockNotesRepository;

import java.util.List;

public class NotesListFragment extends Fragment {

    private NotesListViewModel viewModel;

    private NotesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(NotesListViewModel.class);
    }

    public NotesListFragment() {
        // Required empty public constructor
    }

    public interface NoteClickListener { // для открытия фрагмента с детальной инфой
        void onNoteClicked(Note note);
    }

    private NoteClickListener noteClickListener;

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

        adapter = new NotesAdapter();

        List<Note> notes = new MockNotesRepository().getNotes();

        adapter.setOnNotesListItemClickListener(new NotesAdapter.OnNotesListItemClickListener() {
            @Override
            public void onNotesListItemClick(View view, int position) {
                openNoteDetail(notes.get(position));
            }
        });

        if (savedInstanceState == null) {
            viewModel.requestNotes();
        }

        viewModel.getNotesLiveData().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.addData(notes);
                adapter.notifyDataSetChanged(); // перерисовка списка
            }
        });

        RecyclerView notesList = view.findViewById(R.id.notes_list);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        notesList.setLayoutManager(lm);

        notesList.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.items_separator));

        notesList.addItemDecoration(itemDecoration);
    }

    private void openNoteDetail(Note note) {
        if (noteClickListener != null) {
            noteClickListener.onNoteClicked(note);
        }
    }
}