package gr.artibet.vgames.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

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

        public GalleryViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.galleryImage);

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
    public void onBindViewHolder(@NonNull GalleryViewHolder galleryViewHolder, int i) {
        String image = mImageList.get(i);

        // Load image
        Glide.with(mContext)
                .load(image)
                .centerCrop()
                .placeholder(R.drawable.ic_image_placeholder)
                .into(galleryViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }
}
