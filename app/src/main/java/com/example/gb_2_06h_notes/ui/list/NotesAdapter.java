package com.example.gb_2_06h_notes.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gb_2_06h_notes.R;
import com.example.gb_2_06h_notes.domain.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private ArrayList<Note> data = new ArrayList<>();

    private OnNotesListItemClickListener onNotesListItemClickListener;

    private final Fragment fragment;

    private int longClickedPosition = -1;

    public NotesAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NotesViewHolder holder, int position) {
        Note note = data.get(position);

        holder.id.setText(note.getStringId());
        holder.title.setText(note.getTitle());
        holder.date.setText(note.getDate());

        Glide.with(holder.image)
                .load(note.getImageUrl())
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnNotesListItemClickListener(OnNotesListItemClickListener onNotesListItemClickListener) {
        this.onNotesListItemClickListener = onNotesListItemClickListener;
    }

    public int getLongClickedPosition() {
        return longClickedPosition;
    }

    public void setData(List<Note> toAdd) {

        NotesDiffUtilCallback callback = new NotesDiffUtilCallback(data, toAdd);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);

        data.clear();
        data.addAll(toAdd);

        result.dispatchUpdatesTo(this);
    }

    // Интерфейс для обработки нажатий
    public interface OnNotesListItemClickListener {
        void onNotesListItemClick(View view, int position);
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView title;
        TextView date;
        ImageView image;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            fragment.registerForContextMenu(itemView);

            // Обработчик нажатий на этом NotesViewHolder
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNotesListItemClickListener != null) {
                        onNotesListItemClickListener.onNotesListItemClick(v, getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemView.showContextMenu();
                    longClickedPosition = getAdapterPosition();
                    return true;
                }
            });

            id = itemView.findViewById(R.id.note_item_id);
            title = itemView.findViewById(R.id.note_item_title);
            date = itemView.findViewById(R.id.note_item_date);
            image = itemView.findViewById(R.id.note_item_image);
        }
    }

    public class NotesDiffUtilCallback extends DiffUtil.Callback {

        private final List<Note> oldList;
        private final List<Note> newList;

        public NotesDiffUtilCallback(List<Note> oldList, List<Note> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
        }
    }
}
