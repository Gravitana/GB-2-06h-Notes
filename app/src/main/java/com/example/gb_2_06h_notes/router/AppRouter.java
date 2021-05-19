package com.example.gb_2_06h_notes.router;

import androidx.fragment.app.FragmentManager;

import com.example.gb_2_06h_notes.R;
import com.example.gb_2_06h_notes.domain.Note;
import com.example.gb_2_06h_notes.ui.auth.AuthFragment;
import com.example.gb_2_06h_notes.ui.detail.NoteDetailFragment;
import com.example.gb_2_06h_notes.ui.edit.EditNoteFragment;
import com.example.gb_2_06h_notes.ui.list.NotesListFragment;

public class AppRouter {

    private FragmentManager fragmentManager;

    public AppRouter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void showNotesList() {
        fragmentManager.beginTransaction()
                .replace(R.id.container, new NotesListFragment())
                .commit();
    }

    public void showDetail(Note note) {
        fragmentManager.beginTransaction()
                .replace(R.id.container, NoteDetailFragment.newInstance(note))
                .addToBackStack(null)
                .commit();
    }

    public void editNote(Note note) {
        fragmentManager.beginTransaction()
                .replace(R.id.container, EditNoteFragment.newInstance(note))
                .addToBackStack(null)
                .commit();
    }

    public void showAuth() {
        fragmentManager.beginTransaction()
                .replace(R.id.container, new AuthFragment())
                .commit();
    }

}
