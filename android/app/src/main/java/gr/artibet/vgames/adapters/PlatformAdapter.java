package gr.artibet.vgames.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gr.artibet.vgames.R;
import gr.artibet.vgames.models.Platform;

public class PlatformAdapter extends RecyclerView.Adapter<PlatformAdapter.PlatformViewHolder> {

    private List<Platform> platformList;
    private OnItemClickListener mItemListener;

    // Item click interface
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemListener = listener;
    }

    // Adapter's view holder
    public static class PlatformViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewDesc;

        public PlatformViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            textViewDesc = itemView.findViewById(R.id.tvPlatformDesc);

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
    public PlatformAdapter(List<Platform> platformList){
        this.platformList = platformList;
    }

    public void setPlatformList(List<Platform> platformList) {
        this.platformList = platformList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlatformViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.platform_item, viewGroup, false);
        PlatformViewHolder viewHolder = new PlatformViewHolder(v, mItemListener);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull PlatformViewHolder platformViewHolder, int i) {
        Platform platform = platformList.get(i);
        platformViewHolder.textViewDesc.setText(platform.getDesc());
    }

    @Override
    public int getItemCount() {
        return platformList.size();
    }
}
