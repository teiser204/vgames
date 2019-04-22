package gr.artibet.vgames.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import gr.artibet.vgames.R;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private List<String> mImageList;
    private OnItemClickListener mItemListener;
    private Context mContext;

    // Item click interface
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemListener = listener;
    }

    // Adapter's view holder
    public static class GalleryViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public ProgressBar progressBar;

        public GalleryViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.galleryImage);
            progressBar = itemView.findViewById(R.id.progressBar);

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
    public GalleryAdapter(Context context, List<String> imageList){
        mContext = context;
        mImageList = imageList;
    }

    public void setImageList(List<String> imageList) {
        mImageList = imageList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gallery_item, viewGroup, false);
        GalleryViewHolder viewHolder = new GalleryViewHolder(v, mItemListener);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final GalleryViewHolder galleryViewHolder, int i) {
        String image = mImageList.get(i);

        // Load image
        Glide.with(mContext)
                .load(image)
                .centerCrop()
                .placeholder(R.drawable.ic_image_placeholder)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        galleryViewHolder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        galleryViewHolder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(galleryViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }
}
