package rikamersita.catatankeuanganpribadi.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import rikamersita.catatankeuanganpribadi.CustomOnItemClickListener;
import rikamersita.catatankeuanganpribadi.FormAddUpdateActivity;
import rikamersita.catatankeuanganpribadi.Note;
import rikamersita.catatankeuanganpribadi.R;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewholder> {
    private LinkedList<Note> listNotes;
    private Activity activity;


    public NoteAdapter(Activity activity) {
        this.activity = activity;
    }

    public LinkedList<Note> getListNotes() {
        return listNotes;
    }

    public void setListNotes(LinkedList<Note> listNotes) {
        this.listNotes = listNotes;
    }

    @Override
    public NoteViewholder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_note, viewGroup, false);
        NoteViewholder noteViewholder = new NoteViewholder(view);
        return noteViewholder;
    }

    @Override
    public void onBindViewHolder(NoteViewholder noteViewholder, int position) {
        noteViewholder.tvTitle.setText(getListNotes().get(position).getTitle());
        noteViewholder.tvDate.setText(getListNotes().get(position).getDate());
        noteViewholder.tvTipe.setText(getListNotes().get(position).getSpntipe());
        noteViewholder.tvKategori.setText(getListNotes().get(position).getSpnkategori());
        noteViewholder.tvDescription.setText(getListNotes().get(position).getDescription());
        noteViewholder.cvNote.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallBack() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, FormAddUpdateActivity.class);
                intent.putExtra(FormAddUpdateActivity.EXTRA_POSITION, position);
                intent.putExtra(FormAddUpdateActivity.EXTRA_NOTE, getListNotes().get(position));
                activity.startActivityForResult(intent, FormAddUpdateActivity.REQUEST_UPDATE);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getListNotes().size();
    }

    public class NoteViewholder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate, tvDescription, tvTipe, tvKategori;
        CardView cvNote;

        public NoteViewholder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_item_title);
            tvTipe = (TextView)itemView.findViewById(R.id.tv_tipe);
            tvKategori = (TextView) itemView.findViewById(R.id.tv_kategori);
            tvDate = (TextView) itemView.findViewById(R.id.tv_item_date);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_item_description);
            cvNote = (CardView) itemView.findViewById(R.id.cv_item_note);
        }
    }


}
