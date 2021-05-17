package com.example.gb_2_06h_notes.ui;

import android.os.Bundle;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.gb_2_06h_notes.R;
import com.example.gb_2_06h_notes.domain.Note;
import com.example.gb_2_06h_notes.ui.list.NotesListFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NotesListFragment.NoteClickListener {

    private boolean isLandscape = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isLandscape = getResources().getBoolean(R.bool.isLandscape);

        initToolbar();
        initContent();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);

        initDrawer(toolbar);

//        toolbar.setTitle(R.string.app_title);

        toolbar.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            return navigateFragment(id);
        });
    }

    private void initContent() {
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

    private void initDrawer(Toolbar toolbar) {
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (navigateFragment(id)) {
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
            return false;
        });
    }

    private boolean navigateFragment(int id) {
        if (id == R.id.action_new) {
            doToast(R.string.action_new);
            return true;
        }
        if (id == R.id.action_sort) {
            PopupMenu popupMenuSort = new PopupMenu(MainActivity.this, findViewById(R.id.action_sort));
            popupMenuSort.inflate(R.menu.notes_sort_menu);
            popupMenuSort.show();

            popupMenuSort.setOnMenuItemClickListener(item -> {
                int idSubItem = item.getItemId();
                return navigateFragment(idSubItem);
            });

            return true;
        }
        if (id == R.id.action_sort_title) {
            doToast(R.string.action_sort_title);
            return true;
        }
        if (id == R.id.action_sort_date) {
            doToast(R.string.action_sort_date);
            return true;
        }
        if (id == R.id.action_settings) {
            doToast(R.string.action_settings);
            return true;
        }
        if (id == R.id.action_theme) {
            doToast(R.string.action_theme);
            return true;
        }
        if (id == R.id.action_account) {
            doToast(R.string.action_account);
            return true;
        }
        if (id == R.id.action_favorite) {
            doToast(R.string.action_favorite);
            return true;
        }
        if (id == R.id.action_backup) {
            doToast(R.string.action_backup);
            return true;
        }

        return false;
    }

    private void doToast(int action) {
        Toast.makeText(MainActivity.this, action, Toast.LENGTH_SHORT).show();
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