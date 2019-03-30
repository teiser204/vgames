package gr.artibet.vgames.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import gr.artibet.vgames.R;
import gr.artibet.vgames.models.Game;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private Context mContext;
    private List<Game> mGameList;
    private OnItemClickListener mItemListener;

    // Item click interface
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemListener = listener;
    }

    // Adapter's view holder
    public static class GameViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivImage;
        public TextView tvTitle;
        public TextView tvCompany;
        public RatingBar rbRating;
        public TextView tvRating;

        public GameViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCompany = itemView.findViewById(R.id.tvCompany);
            rbRating = itemView.findViewById(R.id.rbRating);
            tvRating = itemView.findViewById(R.id.tvRating);

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
    public GameAdapter(Context context, List<Game> gameList){

        mContext = context;
        mGameList = gameList;

    }

    public void setmGameList(List<Game> gameList) {
        mGameList = gameList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.game_item, viewGroup, false);
        GameViewHolder viewHolder = new GameViewHolder(v, mItemListener);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder gameViewHolder, int i) {
        Game game = mGameList.get(i);

        // Set game data
        gameViewHolder.tvTitle.setText(game.getTitle());
        gameViewHolder.tvCompany.setText(game.getCompany().getDesc());
        gameViewHolder.rbRating.setRating(game.getRating());
        gameViewHolder.tvRating.setText("(" + game.getRating() + ")");

        // Load image
        Glide.with(mContext)
                .load(game.getImage())
                .centerCrop()
                .placeholder(R.drawable.ic_image_placeholder)
                .into(gameViewHolder.ivImage);

    }

    @Override
    public int getItemCount() {

        return mGameList.size();
    }
}
