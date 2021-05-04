package com.example.gb_2_06h_notes;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.gb_2_06h_notes.domain.Note;

public class MainActivity extends AppCompatActivity implements NotesListFragment.OnNoteClicked {

    private boolean isLandscape = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle(R.string.app_title);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_new) {
                    Toast.makeText(MainActivity.this, R.string.action_new, Toast.LENGTH_SHORT).show();
                    return true;
                }

                if (item.getItemId() == R.id.action_sort) {
                    Toast.makeText(MainActivity.this, R.string.action_sort, Toast.LENGTH_SHORT).show();
                    return true;
                }

                if (item.getItemId() == R.id.action_settings) {
                    Toast.makeText(MainActivity.this, R.string.action_settings, Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            }
        });

        isLandscape = getResources().getBoolean(R.bool.isLandscape);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (isLandscape) {
            Fragment fragment = fragmentManager.findFragmentById(R.id.list_fragment);

            if (fragment == null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.list_fragment, new NotesListFragment())
                        .commit();
            }
        } else {
            Fragment fragment = fragmentManager.findFragmentById(R.id.container);

            if (fragment == null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new NotesListFragment())
                        .commit();
            }
        }
    }

    @Override
    public void onNoteClicked(Note note) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (isLandscape) {
            fragmentManager.beginTransaction()
                    .replace(R.id.details_fragment, NoteDetailFragment.newInstance(note))
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, NoteDetailFragment.newInstance(note))
                    .addToBackStack(null)
                    .commit();
        }

    }
}