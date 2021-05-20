package com.example.gb_2_06h_notes.ui.list;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gb_2_06h_notes.R;
import com.example.gb_2_06h_notes.domain.Constants;
import com.example.gb_2_06h_notes.domain.Note;
import com.example.gb_2_06h_notes.router.AppRouter;
import com.example.gb_2_06h_notes.router.RouterHolder;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> implements Constants, RouterHolder {

    private final Fragment fragment;

    private final ArrayList<Note> data = new ArrayList<>();

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

        holder.id.setText(note.getId());
        holder.title.setText(note.getTitle());
        holder.date.setText(DateFormat.format(DATE_FORMAT, note.getCreatedAt()));

        Glide.with(holder.image)
                .load(note.getImageUrl())
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Note getItemAt(int longClickedPosition) {
        return data.get(longClickedPosition);
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

    /*
        Таким образом, вы не будете раскрывать подробности внутреннего устройства ViewHolder'a
        и его ответственностью становится расположение данных в собственных полях.
        При этом вызывающий код об этом ничего не знает и если в дальнейшем вы захотите поменять способ отображения,
        вы поменяете это только в самом ViewHolder'е, не затрагивая остальной код
     */
    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position, @NonNull List<Object> payloads) {
        Note note = data.get(position);

        holder.bind(note);

        Glide.with(holder.image)
                .load(note.getImageUrl())
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public AppRouter getRouter() {
        return null;
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        private final TextView id;
        private final TextView title;
        private final TextView date;
        private final ImageView image;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            fragment.registerForContextMenu(itemView);

            itemView.setOnLongClickListener(v -> {
                itemView.showContextMenu();
                longClickedPosition = getAdapterPosition();
                return true;
            });

            itemView.setOnClickListener(v -> {
                Note note = data.get(getAdapterPosition());

                AppRouter router = new AppRouter(fragment.getParentFragmentManager());

                router.showDetail(note);
            });

            id = itemView.findViewById(R.id.note_item_id);
            title = itemView.findViewById(R.id.note_item_title);
            date = itemView.findViewById(R.id.note_item_date);
            image = itemView.findViewById(R.id.note_item_image);
        }

        public void bind(Note note) {
            id.setText(note.getId());
            title.setText(note.getTitle());
            date.setText(DateFormat.format(DATE_FORMAT, note.getCreatedAt()));
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
