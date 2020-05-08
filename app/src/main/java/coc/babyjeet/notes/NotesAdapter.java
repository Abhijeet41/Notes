package coc.babyjeet.notes;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import coc.babyjeet.notes.ColorPicker.ColorPickerDialog;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> implements ColorPickerDialog.OnColorChangedListener {

    private Context context;
    private List<Note> notesList;



    public NotesAdapter(MainActivity mainActivity, List<Note> notesList) {
        this.context = mainActivity;
        this.notesList = notesList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note = notesList.get(position);


        holder.note.setText(note.getNote());

        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));

        // Formatting and displaying timestamp
        holder.timestamp.setText(formatDate(note.getTimestamp()));


    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    @Override
    public void colorChanged(int color) {
        MyViewHolder holder = null;
        holder.note.setTextColor(color);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView note;
        public TextView dot;
        public TextView timestamp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            note = itemView.findViewById(R.id.note);
            dot = itemView.findViewById(R.id.dot);
            timestamp = itemView.findViewById(R.id.timestamp);


        }


    }

    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }

}
