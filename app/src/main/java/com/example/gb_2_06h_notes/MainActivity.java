package com.example.gb_2_06h_notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);

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
}