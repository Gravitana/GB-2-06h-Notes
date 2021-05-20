package com.example.gb_2_06h_notes.ui.list;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gb_2_06h_notes.R;
import com.example.gb_2_06h_notes.router.AppRouter;
import com.example.gb_2_06h_notes.router.RouterHolder;
import com.example.gb_2_06h_notes.ui.AddNoteDialogFragment;

public class NotesListFragment extends Fragment {

    private NotesListViewModel viewModel;

    private NotesAdapter adapter;

    public NotesListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(NotesListViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        toolbar.setOnMenuItemClickListener(item -> {

            if (item.getItemId() == R.id.action_new) {

                showAddNoteDialog(); //viewModel.addClicked();

                return true;
            }
            return false;
        });

        adapter = new NotesAdapter(this);

        if (savedInstanceState == null) {
            viewModel.requestNotes();
        }

        viewModel.getNotesLiveData().observe(getViewLifecycleOwner(), notes1 -> {
            adapter.setData(notes1);
            adapter.notifyDataSetChanged(); // перерисовка списка
        });

        RecyclerView notesList = view.findViewById(R.id.notes_list);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        notesList.setLayoutManager(lm);

        notesList.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.items_separator));

        notesList.addItemDecoration(itemDecoration);
    }

    private void showAddNoteDialog() {
        new AddNoteDialogFragment().show(getParentFragmentManager(), "AddNoteDialogFragment");
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        requireActivity().getMenuInflater().inflate(R.menu.menu_list_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_open) {
            if (getActivity() instanceof RouterHolder) {
                AppRouter router = ((RouterHolder) getActivity()).getRouter();
                router.showDetail(adapter.getItemAt(adapter.getLongClickedPosition()));
            }
            return true;
        }

        if (item.getItemId() == R.id.action_update) {
            if (getActivity() instanceof RouterHolder) {
                AppRouter router = ((RouterHolder) getActivity()).getRouter();
                router.editNote(adapter.getItemAt(adapter.getLongClickedPosition()));
            }
            return true;
        }

        if (item.getItemId() == R.id.action_delete) {

            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

            builder.setTitle(R.string.delete_note_message)
                    .setIcon(R.drawable.ic_delete_24)
                    .setCancelable(false)
                    .setPositiveButton(R.string.delete_note_positive, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            viewModel.deleteClicked(adapter.getItemAt(adapter.getLongClickedPosition()));
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(requireContext(), "Negative", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();

            return true;
        }

        return super.onContextItemSelected(item);
    }
}