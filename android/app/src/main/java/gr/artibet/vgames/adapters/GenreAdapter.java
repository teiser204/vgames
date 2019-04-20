package gr.artibet.vgames.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gr.artibet.vgames.R;
import gr.artibet.vgames.models.Genre;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {

    private List<Genre> genreList;
    private OnItemClickListener mItemListener;

    // Item click interface
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemListener = listener;
    }

    // Adapter's view holder
    public static class GenreViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewDesc;

        public GenreViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            textViewDesc = itemView.findViewById(R.id.tvGenreDesc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null ) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    // Constructor
    public GenreAdapter(List<Genre> genreList){
        this.genreList = genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.genre_item, viewGroup, false);
        GenreViewHolder viewHolder = new GenreViewHolder(v, mItemListener);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder genreViewHolder, int i) {
        Genre genre = genreList.get(i);
        genreViewHolder.textViewDesc.setText(genre.getDesc() + " (" + genre.getTotalGames() + ")");
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }
}
